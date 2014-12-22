#!/usr/bin/env ruby
require 'fileutils'
require 'optparse'
require 'debugger'

def main args
  options = {}
  option_parser = OptionParser.new do |opts|
    opts.banner = 'Usage: remove_sub_with_helper.rb [options]'
    opts.on('-s SRC_DIR', 'specify source directory') do |value|
      options[:src_dir] = value
    end
    opts.on('-f FLAG_FILE', 'only copy sources corresponding to zero rows in flag_file') do |value|
      options[:flag_file] = value
    end
    opts.on('-i INDEX_FILE', 'spcify index file') do |value|
      options[:index_file] = value
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
  
  src_dir = options[:src_dir]
  indexes = IO.readlines(options[:index_file]).map(&:to_i)
  output_dir = options[:output_dir]
  flags = IO.readlines(options[:flag_file]).map(&:to_f)
  nonzero_indexes = flags.each_with_index.select { |f_and_index| f_and_index[0] > 0.0 }.map(&:last)
  nonzeros = nonzero_indexes.map { |nind| indexes[nind] }

  Dir.glob(src_dir + "/*.rb").each do |filename|
    /.*\/(.*)\.rb/ =~ filename 
    FileUtils.cp filename, output_dir unless nonzeros.include? $1.to_i 
  end
  
end

main ARGV
