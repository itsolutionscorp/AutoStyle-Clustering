#!/usr/bin/env ruby
require 'ruby_parser'
require 'optparse'
require 'fileutils'
require 'ruby2ruby'
require 'ripper'

class ParserWithHelper
  
  def initialize args
    options = {}
    option_parser = OptionParser.new do |opts|
      opts.banner = 'Usage: ast_with_no_helper.rb [options.rb]' 
      opts.on('-f FILE', 'specify source file') do |value|
        options[:source_dir] = value
      end
      opts.on('-m METHOD', 'specify the method to be extracted') do |value|
        options[:method] = value
      end
      opts.on('-h', '--help', 'Show this message') do
        puts opts
        exit
      end
    end
    option_parser.parse! args

    @source_file = options[:source_dir]
    @method = options[:method]

    parse_without_helper
  end

  def parse_without_helper
    filename = @source_file
    code = IO.readlines filename
    method_sexp = get_method_sexp filename      
    method_sexp.comments = nil
    puts method_sexp.to_s
  end

  def get_all_functions code
    functions = []
    code.each do |line|
      /^\s*def\s+([^\s\(]+)\s?.*/ =~ line
      functions << $1 if $1
    end 
    functions
  end

  def get_method_sexp filename, method=@method
    extract_method(RubyParser.new.process(IO.read(filename)), method)
  end

  def extract_method sexp, method=@method
    if (not sexp) or (sexp.class.to_s != "Sexp") 
      return nil
    end
    if sexp[0].class.to_s == "Symbol" and sexp[0].to_s == "defn" and sexp[1].to_s == method 
      return sexp
    else
      sexp.each do |entry|
        temp = extract_method(entry, method)
        return temp if temp 
      end 
      return nil
    end
  end

end

ParserWithHelper.new(ARGV)
