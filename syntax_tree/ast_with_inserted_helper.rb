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
      opts.banner = 'Usage: ast_with_helper.rb [options.rb]' 
      opts.on('-s SOURCE_DIR', 'specify source directory') do |value|
        options[:source_dir] = value
      end
      opts.on('-o OUTPUT_DIR', 'specify output directory') do |value|
        options[:output_dir] = value
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

    @source_dir = options[:source_dir]
    @output_dir = options[:output_dir]
    @method = options[:method]
    FileUtils.mkdir_p @output_dir unless Dir.exists? @output_dir

    parse_with_helper
    generate_index
  end

  def parse_with_helper
    Dir.glob(@source_dir + "/*.rb").each do |filename|
      code = IO.readlines filename
      functions = get_all_functions code
      method_sexp = get_method_sexp filename      
      method_sexp.comments = nil
      method_code = Ruby2Ruby.new.process method_sexp
      tokens = Ripper.tokenize(method_code)
      /.*\/(.*)\.rb/ =~ filename
      f = open(@output_dir + "/#{$1}_ast", "w+")
      functions = functions.select { |func| tokens.include? func } 

      # Write @method and its helper functions to file
      s = "s(:block, "
      s << get_method_sexp(filename, @method).to_s
      s << ")"
      
      functions.each_with_index do |func, i|
        if func != @method
          s = insert_helper_function(s, filename, func)
        end
      end
      f.write s
      f.close
    end
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

  def insert_helper_function sexp, filename, func_name
    sexp_array = get_method_sexp(filename, func_name)
    helper_sexp_body = sexp_array[3..sexp_array.length].to_s
    insert_sexp = helper_sexp_body.insert(2, ':helper ')
    sexp.gsub(':' + func_name, insert_sexp)
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

  def generate_index 
    f = open(@output_dir + "/array_index", "w+")
    Dir.glob(@output_dir + "/*_ast").sort.each do |filename|
      /.*\/(.*)_ast/ =~ filename
      f.puts $1
    end
    f.close
  end
end

ParserWithHelper.new(ARGV)
