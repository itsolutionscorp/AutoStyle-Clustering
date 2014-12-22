#!/usr/bin/env ruby

require 'benchmark'
require 'timeout'
require 'parallel'
require 'fileutils'
TIMEOUT_SECS = 10

class CodeTimer

	attr_accessor :source_dir
  attr_accessor :output_dir
	attr_accessor :method_name
	attr_accessor :data

	def initialize source_dir, output_dir, method_name, data_file
		@source_dir = source_dir
    @output_dir = output_dir 
		@method_name = method_name
    @data = IO.readlines(data_file)
	end

  def puts (*args)
    # Do nothing
  end

  def print (*args)
    # Do nothing
  end

  def printf (*args)
    # Do nothing
  end

  def putc (*args)
    # Do nothing
  end

	def time
	  FileUtils.mkdir_p @output_dir unless Dir.exists? @output_dir	
    Parallel.each(Dir[@source_dir + '/*.rb'].sort, :in_process => 32) do |file_num|
      /.*\/([^\/]+).rb$/ =~ file_num
		  begin
				$stderr.puts file_num  
				require_relative file_num
				arg_words = @data.clone
        Timeout::timeout(TIMEOUT_SECS) do
					time = Benchmark.realtime do
						send(@method_name, arg_words)
					end
          f = open(File.join(@output_dir, $1+"_output"), "w+")
					f.puts time.to_s + " " + $1  
          f.close
        end

			rescue Timeout::Error
				$stderr.puts "combine_anagrams is too slow!"
        f = open(File.join(@output_dir, $1+"_output"), "w+")
			  f.puts TIMEOUT_SECS.to_s + " " + $1 
        f.close

			rescue Exception
				$stderr.puts "Error when running combine_anagrams!"
				$stderr.puts $!.inspect, $@
        f = open(File.join(@output_dir, $1+"_output"), "w+")
			  f.puts "-1 " + $1 
        f.close
			end
	  end
  end
end

ct = CodeTimer.new(ARGV[0], ARGV[1], ARGV[2], ARGV[3])
ct.time

