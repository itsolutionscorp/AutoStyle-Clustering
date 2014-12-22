#! /usr/bin/env ruby
require 'ruby_parser'

def parse_source_into_ast input_dir
  output_dir = input_dir.gsub(Regexp.new('/[^/]*\/?$'), '/ast686')
  Dir.mkdir output_dir unless Dir.exists? output_dir
  ef = File.open File.join(output_dir, "error_submission_list"), "w+"
  Dir.glob(File.join(input_dir, "*.rb")) do |filename|      
    code = IO.read filename
    begin
      ast = RubyParser.new.process code
      /.*\/(.*)\.rb/ =~ filename
      File.open File.join(output_dir, $1 + "_ast"), "w+" do |f|
        f.write ast
        f.close
      end 
    rescue
      ef.write filename + "\n"
    end
  end
  ef.close
  generate_index output_dir
end

def generate_index input_dir
  f = File.open File.join(input_dir, "array_index"), "w+"
  Dir.glob(File.join(input_dir, "*_ast")).sort.each do |filename|      
    /.*\/(.*)_ast/ =~ filename
    f.write $1 + "\n"
  end
  f.close
end

parse_source_into_ast ARGV[0]
