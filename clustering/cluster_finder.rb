#!/usr/bin/env ruby
require 'fileutils'
require 'optparse'

class ClusterFinder
  
  attr_accessor :source_dir
  attr_accessor :indexes
  attr_accessor :cluster_file
  attr_accessor :cluster_dir
  attr_accessor :running_times
  attr_accessor :kmedoids

  def initialize args
    options = {}
    option_parser = OptionParser.new do |opts|

      opts.banner = 'Usage: cluster_finder.rb [options]'

      opts.on('-s SRC_DIR', '--src-dir SRC_DIR', 'specify source directory') do |value|
        options[:source_dir] = value
      end

      opts.on('-i INDEX_FILE', '--index-file INDEX_FILE', 'specify index file') do |value|
        options[:index_file] = value
      end

      opts.on('-c CLUSTER_FILE', '--cluster-file CLUSTER_FILE', 'specify cluster file') do |value|
        options[:cluster_file] = value
      end

      opts.on('-o CLUSTER_DIR', '--output CLUSTER_DIR', 'specify output directory') do |value|
        options[:cluster_dir] = value
      end

      opts.on('-e RUNTIME_FILE', '--error RUNTIME_FILE', 'delete error submissions whose row in RUNTIME_FILE has -1') do |value|
        options[:rt_file] = value
      end
      
      opts.on_tail('-h', '--help', 'Show this message') do 
        puts opts
        exit
      end
    end   
    option_parser.parse! args
    @source_dir = options[:source_dir] 
    @indexes = IO.readlines(options[:index_file]).map { |line| line.to_i }
    @cluster_file = options[:cluster_file]
    @cluster_dir = options[:cluster_dir]
    FileUtils.mkdir_p @cluster_dir unless Dir.exists? @cluster_dir or @cluster_dir.nil?
    @running_times = IO.readlines(options[:rt_file]).map { |line| line.split.map(&:to_f) } if options[:rt_file] 

    # Do the job
    find_cluster
    running_time if @running_times 

  end

  def find_cluster
    cmap = IO.readlines(@cluster_file).map { |line| line.to_f }
    cmap.uniq.each { |c| FileUtils.mkdir_p File.join(@cluster_dir, @indexes[c].to_s) }
    puts "Number of submissions can't be executed: " + @running_times.select {|a| a.include? -1.0}.length.to_s if @running_times
    @indexes.each_with_index do |item, i|
      if @running_times.nil? or not @running_times[i].include? -1.0
        FileUtils.cp File.join(@source_dir, item.to_s + ".rb"), File.join(@cluster_dir, @indexes[cmap[i]].to_s)
      end
    end
    if not @kmedoids
      fn_hash = {}
      Dir.glob(@cluster_dir + "/*").each do |dirname|
        /.*\/([^\/]+)$/ =~ dirname
        fn_hash[$1] = Dir.glob(dirname + "/*.rb").length
      end
      corder = fn_hash.sort_by { |k, v| -v }.map { |a| a[0] }
      Dir.glob(@cluster_dir + "/*").each do |dirname|
        /(.*)\/([^\/]+)$/ =~ dirname
        FileUtils.mv dirname, $1 + "/" + (corder.index($2)+1).to_s
      end
    end
  end

  private

    def running_time
      Dir.glob(@cluster_dir + "/*").each do |dirname|
        carray = cluster_running_time(dirname)
        puts File.basename(dirname) + "(Ana): " + dp(carray[0].max) + "   " + dp(carray[0].mean) + "   " + dp(carray[0].min)
        puts File.basename(dirname) + "(Cro): " + dp(carray[1].max) + "   " + dp(carray[1].mean) + "   " + dp(carray[1].min)
      end
    end

    def cluster_running_time input_dir
      result = Array.new(2) { Array.new }
      Dir.glob(input_dir + "/*.rb").each do |filename|
        /.*\/([^\/]+).rb$/ =~ filename 
        temp = @running_times[@indexes.index($1.to_i)]
        result[0] << temp[0] 
        result[1] << temp[1]
      end
      return result
    end

    def dp f
      /^.*\.\d\d?\d?/.match(f.to_s)[0]
    end
  
end

module Enumerable

  def sum
    self.reduce(0) { |accum, i| accum + i }
  end
  
  def mean
    self.sum / self.length.to_f
  end

end

ClusterFinder.new ARGV

