#!/usr/bin/env ruby
require 'fileutils'
require 'optparse'
require 'gruff'
require 'ap'
require 'debugger'

class FWDPlotter
  
  def initialize args
    options = {}
    option_parser = OptionParser.new do |opts|
      opts.banner = 'Usage: factor_weight_distribution.rb [options]'
      opts.on('-d SUBS_FACTOR_DATA', 'specify data file') do |value|
        options[:data_file] = value
      end
      opts.on('-i INDEX_FILE', 'specify array_index for this dataset') do |value|
        options[:index_file] = value
      end
      opts.on('-o OUTPUT_DIR', 'specify output directory') do |value|
        options[:output_dir] = value
      end
      opts.on('-c CLUSTER_FILE', 'specify cluster file') do |value|
        options[:cluster_file] = value
      end
      opts.on_tail('-h', '--help', 'Show this message') do 
        puts opts
        exit
      end
    end
    option_parser.parse! args 

    @dataset = get_data(options[:data_file])
    @indexes = IO.readlines(options[:index_file]).map { |line| line.to_i }
    @output_dir = options[:output_dir]
    @cluster = process_cluster_data(IO.readlines(options[:cluster_file]).map(&:to_f))
  end

  def plot
    @cluster.each do |c|
      cluster_dir = @output_dir + "/#{c.length.to_s}" 
      FileUtils.mkdir cluster_dir unless Dir.exists? cluster_dir
      c.each do |ind|
        g = setup_basic_plot @dataset[ind]
        g.title = "Bar Chart for #{@indexes[ind].to_s}.rb"
        g.write(cluster_dir + "/#{@indexes[ind].to_s}.png")
      end
    end
  end

  def setup_basic_plot sub_data, size=800
    g = Gruff::Bar.new(size)
    g.title = "Bar Chart"
    sub_data.each do |data|
      g.data(data[0], data[1])
    end
    g.maximum_value = 0.4
    g.minimum_value = 0.0
    g
  end

=begin
  example dataset for gruff bar chart:
    dataset = [
      [1, [0.4]], 
      [2, [0.3]],
      [3, [0.2]],
      [4, [0.1]]
    ]
=end

  def get_data data_file
    subs = IO.read(data_file).split(/# submission \d+/).map { |sub| sub.strip.lines.map(&:chomp).map(&:split)} 
    subs = subs.map { |sub| sub.map { |factor| [factor[0].to_i, [factor[1].to_f]]}}
    subs.delete_at(0)
    subs
  end

  # Preprocessor to construct nested array @cluster
  def process_cluster_data cluster_data
    cluster_data.zip((0..cluster_data.length-1).to_a).group_by { |array| array[0] }.values.map { |a| a.map { |b| b[1] }}
  end

end

fwd_plotter = FWDPlotter.new(ARGV)
fwd_plotter.plot

