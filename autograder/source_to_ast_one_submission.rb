'''
This script generates the AST of a ruby source file and adds it to the output directory.
Inputs: source file, output directory for the source files AST, the relevant method
'''


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
      opts.on('-s SOURCE_FILE', 'specify file path') do |value|
        options[:filename] = value
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

    @filename = options[:filename]
    @output_dir = options[:output_dir]
    @method = options[:method]
    FileUtils.mkdir_p @output_dir unless Dir.exists? @output_dir

    parse_without_helper(@filename)
    generate_index
  end

  def parse_without_helper filename 
    code = IO.readlines filename
    functions = get_all_functions code
    method_sexp = get_method_sexp filename      
    method_sexp.comments = nil
    method_code = Ruby2Ruby.new.process method_sexp
    tokens = Ripper.tokenize(method_code)
    /.*\/(.*)\.rb/ =~ filename
    f = open(@output_dir + "/#{$1}_ast", "w+")
    functions = functions.select { |func| tokens.include? func } 
    # Write @method to file
    f.write get_method_sexp(filename, @method).to_s
    f.close
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
