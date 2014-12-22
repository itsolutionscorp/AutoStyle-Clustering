#!/usr/bin/env ruby
require_relative 'silhouette'
require_relative 'basic_statistic'
require 'fileutils'
require 'optparse'
require 'debugger'
require 'gruff'
require 'ap'

class ClusteringValidator

  def initialize args
    options = {}
    option_parser = OptionParser.new do |opts|
      opts.banner = 'Usage: validator.rb [options]'
      opts.on('-c CLUSTER_FILE', 'specify cluster file') do |value|
        options[:cluster_file] = value
      end
      opts.on('-f FEATURE_FILE', 'specify feature file') do |value|
        options[:feature_file] = value
      end
      options[:high_dimension] = false
      opts.on('-d', 'indicate feature is in high dimension') do 
        options[:high_dimension] = true
      end
      opts.on('-o OUTPUT_DIR', 'specify output directory') do |value|
        options[:output_dir] = value
      end
      opts.on_tail('-h', '--help', 'Show this message') do 
        puts opts
        exit
      end
    end
    option_parser.parse! args 

    # @cluster is a nested array, each sub-array represents a cluster
    @cluster = process_cluster_data(IO.readlines(options[:cluster_file]).map(&:to_f))
    @nc = @cluster.length
    @output_dir = options[:output_dir]
    FileUtils.mkdir_p @output_dir unless Dir.exists? @output_dir
    if options[:high_dimension]
      @feature = IO.readlines(options[:feature_file]).map { |line| line.split.map(&:to_f)}
      puts silhouette_scikit(@feature, @cluster)
      pretty_print silhouette_cluster(@feature, @cluster)
    else
      @feature = IO.readlines(options[:feature_file]).map(&:to_f)
      pretty_print ld_mean
      pretty_print ld_standard_deviation
      pretty_print ld_variation_coefficient
      pretty_print ld_best_submission

      scatter_overlap [93, 310, 148, 204, 44]
      scatter_each_cluster
      scatter_distribution
      scatter_distribution_each_cluster
      scatter_distribution_overlap [93, 310, 148, 204, 44]

      outlier_and_helper_function

#bar_mean
#inspect_cluster 93
    end
  end

  # Preprocessor to construct nested array @cluster
  def process_cluster_data cluster_data
    cluster_data.zip((0..cluster_data.length-1).to_a).group_by { |array| array[0] }.values.map { |a| a.map { |b| b[1] }}
  end

  # Helper to compute Euclidean distance between two vector
  def dist(a1, a2)
    Math.sqrt(a1.zip(a2).map { |x| (x[1] - x[0])**2 }.reduce(:+))
  end

  # Compute variance vector for low dimension feature
  def ld_variance
    variances = Array.new(@nc, 0) 
    @cluster.each_with_index do |subc, i|
      variances[i] = @feature.each_with_index.select { |f, j| subc.include? j }.map { |f_and_index| f_and_index[0] }.sample_variance
    end
    return variances
  end

  # Compute standard deviation vector for low dimension feature
  def ld_standard_deviation
    ld_variance.map { |e| Math.sqrt(e) }
  end

  # Compute mean vector for low dimension feature
  def ld_mean
    means = Array.new(@nc, 0)
    @cluster.each_with_index do |subc, i|
      means[i] = @feature.each_with_index.select { |f, j| subc.include? j }.map { |f_and_index| f_and_index[0] }.mean
    end
    return means
  end

  def ld_variation_coefficient
    ld_standard_deviation.zip(ld_mean).map { |a| a[0].to_f / a[1] }
  end

  def ld_best_submission
    best_indexes = Array.new(@nc)
    @cluster.each_with_index do |subc, i|
      best_indexes[i] = @feature.each_with_index.select { |f, j| subc.include? j}.sort_by(&:first)[0]
    end
    best_indexes
  end

  def pretty_print vector
    ap Hash[vector.zip(@cluster.map { |subc| subc.length })]
  end

  
  # Defined for debug, you can examine a particular cluster's feature
  # Compatible with both low dimension and high dimension feature
  def inspect_cluster size_of_cluster
    @cluster.each do |c|
      if c.length == size_of_cluster
        c.each do |s|
          puts "#{s.to_s}: #{@feature[s].to_s}"
        end
      end
    end
  end

  # Compute x-axis for scatter plot 
  def get_point_order
    order = Array.new(@feature.length)
    @feature.each_with_index.sort_by(&:first).each_with_index { |f_and_index, i| order[f_and_index[1]] = i }
    order
  end

  # To make the point smaller, I changed /Users/Beaver/.rvm/gems/ruby-1.9.3-p484/gems/gruff-0.5.1/lib/gruff line 99 circle_radius
  def scatter_overlap carray, size=1600
    order = get_point_order
    g = Gruff::Scatter.new(size)
    @cluster.each do |subc|
      next unless carray.include? subc.length
      tuple_array = @feature.zip(order).each_with_index.select { |f_and_order, i| subc.include? i }
      x = tuple_array.map(&:first).map(&:last)
      y = tuple_array.map(&:first).map(&:first)
      g.data(subc.length, x, y)
    end
    g.title = "Scatter Plot"
    g.write(@output_dir + "/scatter_overlap.png")
  end

  def scatter_each_cluster size=1600
    order = get_point_order
    @cluster.each do |subc| 
      g = Gruff::Scatter.new(size)
      g.marker_count = 10
      tuple_array = @feature.zip(order).each_with_index.select { |f_and_order, i| subc.include? i }
      x = tuple_array.map(&:first).map(&:last)
      y = tuple_array.map(&:first).map(&:first)
      g.data(subc.length, x, y)
      g.maximum_value = 87.0
      g.minimum_value = 2.0
      g.title = "Scatter Plot"
      g.write(@output_dir + "/scatter_each_cluster_#{subc.length}.png")
    end
  end 

  def scatter_distribution size=1600
    hash = @feature.group_by { |f| f.round }
    x = []; y = []
    hash.sort.each do |key, value|
      x << key
      y << value.length
    end
    g = Gruff::Scatter.new(size)
    g.data(@feature.length, x, y)
    g.marker_count = 10 
    g.title = "Distribution of score"
    g.write(@output_dir + "/distribtion.png")
  end

  def scatter_distribution_each_cluster size=1600
    @cluster.each do |subc|
      subf = @feature.each_with_index.select { |f, i| subc.include? i }.map(&:first)
      subh = subf.group_by { |f| f.round }
      x = []; y = []
      subh.sort.each do |key, value|
        x << key
        y << value.length
      end 
      g = Gruff::Scatter.new(size)
      g.data(subf.length, x, y)
      g.marker_count = 10
      g.title = "Each cluster's distribution"
      g.maximum_value = 58.0
      g.minimum_value = 1.0
      g.write(@output_dir + "/distribution_each_cluster_#{subf.length}.png")
    end
  end

  def scatter_distribution_overlap carray, size=1600
    g = Gruff::Scatter.new(size)
    g.marker_count = 10
    @cluster.each do |subc|
      next unless carray.include? subc.length
      subf = @feature.each_with_index.select { |f, i| subc.include? i }.map(&:first)
      subh = subf.group_by { |f| f.round }
      x = []; y = []
      subh.sort.each do |key, value|
        x << key
        y << value.length
      end 
      g.data(subf.length, x, y)
    end 
    g.title = "Compare distribution between clusters"
    g.maximum_value = 58.0
    g.minimum_value = 1.0
    g.write(@output_dir + "/compare_distribution_#{carray.to_s}.png")
  end

  def outlier_and_helper_function
    inc_array = IO.readlines("/Users/Beaver/CS169_Clustering/800/feature795/increment_flog_feature.np").map(&:to_i) 
    helper_indexes = inc_array.each_with_index.select { |inc_and_index| inc_and_index[0] > 0 }.map(&:last) 
    outlier_indexes = []
    @cluster.each do |subc|
      if [48, 37, 33].include? subc.length
        outlier_indexes += subc 
      end
    end
    shared = helper_indexes & outlier_indexes
    ap shared
    singular_helpers = helper_indexes - shared
    ap singular_helpers

    singular_helpers.each do |s|
      @cluster.each do |subc|
        if subc.include? s
          puts [s, subc.length].to_s
        end
      end
    end
    
  end

#def bar_mean size=1600
#    g = Gruff::Bar.new(size)
#    debugger
#    g.title = "Mean score of all clusters" 
#  end
end

ClusteringValidator.new(ARGV)
