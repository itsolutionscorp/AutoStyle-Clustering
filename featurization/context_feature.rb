#!/usr/bin/env ruby

require 'ruby_parser'
require 'fileutils'
require 'optparse'
require 'debugger'
require 'json'
require 'set'

=begin

currently can't deal with "groups[key] = [word]"

=end

class CLFGenerator
  
  def initialize args
    options = {}
    option_parser = OptionParser.new do |opts|
      opts.on('-s SRC_DIR', 'specify source directory') do |value|
        options[:src_dir] = value
      end
      opts.on('-i INDEX_FILE', 'specify directory') do |value|
        options[:index_file] = value 
      end
      opts.on('-f FEATURE_DIR', 'specify feature directory') do |value|
        options[:feature_dir] = value
      end 
      opts.on_tail('-h', '--help', 'Show this message') do 
        puts opts
        exit
      end
    end
    option_parser.parse! args

    @src_dir = options[:src_dir]
    @indexes = IO.readlines(options[:index_file]).map { |line| line.to_i }
    @feature_dir = options[:feature_dir]
    @iter_functions = [:each, :map, :each_index, :group_by, :each_with_index, :each_value, :collect, :find, :each_key, :each_with_object, :select, :map!, :reject!, :each_char, :each_byte, :inject, :each_pair, :collect!, :reject, :find_all, :upto, :reduce, :partition, :permutation, :cycle, :chars]

  end

  def featurize
    generate_context_library_feature
    context_library_names = get_context_library_functions_list
    File.open(@feature_dir + "/context_feature_names.np", "w+") do |f|
      0.upto(context_library_names.length - 1) { |i| f.puts context_library_names[i]}
    end
    context_library_feature = get_context_library_feature
    
    write_feature_to_file context_library_feature, @feature_dir + "/context_library_call.np"

    nonzero_array = nonzero_count context_library_feature
    temp = nonzero_array.zip(context_library_names).each_with_index.select { |count_and_name, i| count_and_name[0] > 1}
    # example for temp[0]: [[588, "[:each, 0, 0]"], 0]
    context_library_names_lt1 = temp.map { |t| t[0][1] }
    File.open(@feature_dir + "/context_feature_names_lt1.np", "w+") do |f|
      0.upto(context_library_names_lt1.length - 1) { |i| f.puts context_library_names_lt1[i]}
    end

    context_library_indexes_lt1 = temp.map { |t| t[1] } # indicates which column(feature) to keep 
    puts "[# of all context library functions called more than once]: " + context_library_indexes_lt1.length.to_s 
    context_library_feature_lt1 = keep_column(context_library_feature, context_library_indexes_lt1) 
    write_feature_to_file context_library_feature_lt1, @feature_dir + "/context_library_call_lt1.np"
  end

  def keep_column feature, column_to_keep_array
    new_feature = Array.new(feature.length) { Array.new }
    feature.each_with_index do |r, i|
      new_row = Array.new(column_to_keep_array.length, 0)
      column_to_keep_array.each_with_index do |c, j|
        new_row[j] = feature[i][c] 
      end
      new_feature[i] = new_row
    end
    new_feature
  end

  def generate_context_library_feature
    output_dir = @feature_dir + "/context_library_function"
    FileUtils.mkdir_p output_dir unless Dir.exists? output_dir 
    library_functions = [].to_set
    ["String", [], {}, //, "Class"].each { |category| library_functions.merge category.methods}
    Dir.glob(@src_dir + "/*.rb").each do |filename|
      function_calls = {}
#recursive_count(RubyParser.new.process(IO.read(filename)), 0, 0, 0, function_calls)
      #debugger
      function_calls = new_recursive_count(RubyParser.new.process(IO.read(filename)))
      function_calls.keep_if { |key, value| library_functions.include? key[0].to_sym}
      /.*\/(.*)\.rb$/ =~ filename
      File.open(output_dir + "/#{$1.to_s}.json", "w+") do |f|
        f.write function_calls.to_json
      end 
    end
  end 

  # add c3 to handle iter situation
  def recursive_count sexp, c1=0, c2=0, c3=0, output={}
    # return if sexp is a leaf
    if sexp.class != Sexp.new.class
      return nil
    end
    if sexp[0] == :call
      # Roll back context is iteration is invoked by this call
      key = (@iter_functions.include? sexp[2]) ? [sexp[2], c2, c3] : [sexp[2], c1, c2]
      if output[key] 
        output[key] += 1
      else
        output[key] = 1
      end
    elsif sexp[0] == :if
      c3 = c2
      c2 = c1
      c1 = 1 # 1 represents condition
    elsif [:until, :for, :while, :iter].include? sexp[0]
      c3 = c2
      c2 = c1
      c1 = 2 # 2 represents iteration 
    end 
    sexp.each do |sub_sexp| 
      recursive_count(sub_sexp, c1, c2, c3, output)
    end
  end

  def new_recursive_count sexp
    return Hash.new unless Sexp === sexp
    recursive_array = []
    sexp.each do |sub_sexp|
      recursive_array << new_recursive_count(sub_sexp)
    end
    case sexp[0]
    when :until, :for, :while, :if 
      context =  (sexp[0] == :if) ? 1 : 2
      return append_context_and_merge_hash_array(recursive_array, context)
    when :iter
      return recursive_array.each_with_index.map { |h, i| if i == 1; h; else; h.each_pair { |k, v| k << 2 }; end }.inject(Hash.new) { |r, h| r.merge(h) {|key, oldval, newval| newval + oldval } }
    when :call
      recursive_array << { [sexp[2]] => 1 }
      return recursive_array.inject(Hash.new) { |r, h| r.merge(h) { |key, oldval, newval| newval + oldval } }
    else
      return recursive_array.inject(Hash.new) { |r, h| r.merge(h) { |key, oldval, newval| newval + oldval } }
    end
  end 

  def append_context_and_merge_hash_array array, context
    array.map { |h| h.each_pair { |k, v| k << context } }.inject(Hash.new) { |r, h| r.merge(h) { |key, oldval, newval| newval + oldval } }
  end

  def get_context_library_feature
    context_library_functions = get_context_library_functions_list
    puts "[# of all context library functions called]: " + context_library_functions.length.to_s 

    context_library_call_feature = Array.new(@indexes.length) { Array.new(context_library_functions.length, 0)}

    @indexes.each_with_index do |index, i|
      hash = JSON.parse(IO.read(@feature_dir + "/context_library_function/#{index.to_s}.json"))
      context_library_functions.each_with_index do |cf, j|
        if hash[cf]
          context_library_call_feature[i][j] = hash[cf]
        end
      end      
    end
    return context_library_call_feature
  end

  def get_context_library_functions_list
    context_library_functions = []
    @indexes.each do |index|
      context_library_functions += JSON.parse(IO.read(@feature_dir + "/context_library_function/#{index.to_s}.json")).keys
    end
    context_library_functions.uniq
  end

  def nonzero_count feature
    nonzero_count = Array.new(feature[0].length, 0) 
    0.upto(feature[0].length - 1) do |i|
      0.upto(feature.length - 1) do |j|
        nonzero_count[i] += 1 if feature[j][i] != 0  
      end
    end
    nonzero_count
  end

  def write_feature_to_file feature, filename
    f = open(filename, "w+")
    0.upto(feature.length-1) do |i|
      0.upto(feature[0].length-1) do |j|
        f.write feature[i][j].to_f
        if j < (feature[0].length-1)
          f.write " "
        end
      end
      if i < (feature.length-1) 
        f.write "\n"
      end
    end 
    f.close
  end

end

g = CLFGenerator.new(ARGV)
g.featurize
