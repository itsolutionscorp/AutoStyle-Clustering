#!/usr/bin/env ruby
require 'fileutils'
require 'ripper'
require 'ruby_parser'

# Extract latest and valid submissions from original folder

def delete_if_no filename, methodname
  return unless File.exists? filename
  error = open(File.join(filename.gsub(/\/[^\/]+\/?$/, ''), "no_method_error"), "a")
  code = IO.read filename
  if not Ripper.tokenize(code).include? methodname
    puts "NoMethodError: " + File.basename(filename)
    error.puts File.basename(filename)
    FileUtils.rm [filename] # rm takes a list as input
  end
  error.close
end

def delete_if_parse_error filename
  return unless File.exists? filename
  error = open(File.join(filename.gsub(/\/[^\/]+\/?$/, ''), "parse_error"), "a")
  code = IO.read filename
  begin
    ast = RubyParser.new.process code
  rescue
    puts "ParseError: " + File.basename(filename)
    error.puts File.basename(filename)
    FileUtils.rm [filename]
  end
  error.close
end

orig_source_dir = ARGV[0]
methodname = ARGV[1]
latest_source_dir = orig_source_dir.gsub(/\/?$/, '') + "_latest"
FileUtils.mkdir_p latest_source_dir unless Dir.exists? latest_source_dir
Dir.glob(File.join(orig_source_dir, '**/*_output')).group_by { |f| f.gsub(Regexp.new('/[^/]+$'), '') }.each_pair do |dir, files|
#Dir.glob(File.join(orig_source_dir, '**/final.rb')).group_by { |f| f.gsub(Regexp.new('/[^/]+$'), '') }.each_pair do |dir, files|
  latest_filename = File.basename files.max
  /(.*)_output/ =~ latest_filename
  output_path = latest_source_dir + "/" + $1 + ".rb" 
  FileUtils.cp File.join(dir, latest_filename), output_path
  delete_if_no output_path, methodname
  delete_if_parse_error output_path
end



