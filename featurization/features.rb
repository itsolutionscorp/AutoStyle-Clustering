#!/usr/bin/env ruby

require 'fileutils'
require 'ruby2ruby'
require 'ruby_parser'
require 'optparse'
require 'tempfile'
require 'ripper'
require 'reek'
require 'json'
require 'set'

require 'debugger'

class FeatureGenerator

  def initialize args
    options = {}
    option_parser = OptionParser.new do |opts|
      opts.banner = 'Usage: features.rb [options]'
      opts.on('-s SRC_DIR', 'specify source directory') do |value|
        options[:src_dir] = value
      end
      opts.on('-f FEATURE_DIR', 'specify output directory for features') do |value|
        options[:feature_dir] = value
      end
      opts.on('-m METHOD', 'specify method to be examined') do |value|
        options[:method] = value
      end
      opts.on('-i INDEX_FILE', 'specify index file') do |value|
        options[:index_file] = value
      end
      options[:helper_flag] = false
      opts.on('--helper-function', 'consider helper functions') do |value|
        options[:helper_flag] = true
      end
      opts.on_tail('-h', '--help', 'Show this message') do 
        puts opts
        exit
      end
    end
    option_parser.parse! args

    @source_dir = options[:src_dir]
    @feature_dir = options[:feature_dir]

    # Create feature directory if not exists
    FileUtils.mkdir @feature_dir unless Dir.exists? @feature_dir

    @method = options[:method]
    @indexes = IO.readlines(options[:index_file]).map { |line| line.to_i }
    @helper_flag = options[:helper_flag] 

    # put clean source directory under feature directory
    @method_source_dir = @feature_dir + "/method_source"
    @whelper_source_dir = @feature_dir + "/whelper_source"

    @all_smells = ["Attribute", "BooleanParameter", "ClassVariable", "ControlParameter", "DataClump", 
  "DuplicateMethodCall", "FeatureEnvy", "IrresponsibleModule", "LongParameterList", "LongYieldList",
  "NestedIterators", "NilCheck", "PrimaDonnaMethod", "RepeatedConditional", "TooManyInstanceVariables",
  "TooManyMethods", "TooManyStatements", "UncommunicativeMethodName", "UncommunicativeModuleName",
  "UncommunicativeParameterName", "UncommunicativeVariableName", "UnusedParameters", "UtilityFunction"]
    @filter_smells= ["TooManyStatements", "ClassVariable", "FeatureEnvy", "UncommunicativeModuleName"]
    @control_flow_keywords = ["for", "while", "until", "if", "elsif", "else"] # TODO when?
  end

  def featurize

    build_method_source_dir unless Dir.exists? @method_source_dir
    build_whelper_source_dir unless Dir.exists? @whelper_source_dir

    generate_library_call_feature unless Dir.exists? @feature_dir + "/library_call"
    puts "--- Library calls files generated! ---"

    generate_control_flow_feature unless Dir.exists? @feature_dir + "/control_flow"
    puts "--- Control flow feature files generated! ---"

    generate_method_length_feature unless File.exists? @feature_dir + "/method_length.json"
    puts "--- Method length feature file generated! ---"
    
    generate_reek_feature unless File.exists? @feature_dir + "/reek.json" 
    puts "--- Reek feature generated! ---"

    generate_flog_feature unless Dir.exists? @feature_dir + "/flog"
    puts "--- Flog feature generated! ---"

#generate_saikuro_feature unless Dir.exists? @feature_dir + "/saikuro"
#puts "--- Saikuro feature generated! ---" 

    # Generate count features
#generate_library_call_count_feature

#   generate_control_flow_count_feature

   generate_reek_count_feature

    #library_call_feature = get_library_call_feature

    #control_flow_feature = get_control_flow_feature
    
   method_length_feature = get_method_length_feature

   reek_feature = get_reek_feature

    flog_feature = get_flog_feature

    write_column_feature_to_file flog_feature, @feature_dir + "/flog_feature.np"
    write_column_feature_to_file method_length_feature, @feature_dir + "/method_length_feature.np"
    write_feature_to_file reek_feature, @feature_dir + "/reek_feature.np"

    all_feature = Array.new(@indexes.length) { Array.new }

    0.upto(@indexes.length - 1) do |i|
      all_feature[i] += control_flow_feature[i]
      all_feature[i] += library_call_feature[i]
#     all_feature[i] << method_length_feature[i] #TODO method length feature's value is too large
#     all_feature[i] += reek_feature[i]
#     all_feature[i] << flog_feature[i] #TODO flog feature's value is too large
    end

    puts "[Number of submissions]: " + all_feature.length.to_s
    puts "[Dimension of all features]: " + all_feature[0].length.to_s

    write_feature_to_file all_feature, @feature_dir + "/all_feature.np"

    # write feature names to file
    library_functions = get_library_functions_list
    feature_names = @control_flow_keywords + library_functions
    File.open(@feature_dir + "/feature_names.np", "w+") do |f|
      0.upto(feature_names.length - 1) { |i| f.puts feature_names[i] }
    end
    puts "--- Library feature list file generated! ---"

  end

  # Helper function 

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

  # Helper function for getting sexp for @method

  def get_method_sexp filename, method=@method
    extract_method(RubyParser.new.process(IO.read(filename)), method)
  end

  # Helper function to get all function names by matching def xxx
  
  def get_all_functions src_file
    code = IO.readlines(src_file)
    functions = []
    puts src_file
    code.each do |line|
      /^\s*def\s+([^\s\(]+)\s?.*/ =~ line
      functions << $1 if $1
    end 
    functions
  end

  # Only extract source of @method

  def build_method_source_dir
    FileUtils.mkdir @method_source_dir unless Dir.exists? @method_source_dir 
    Dir.glob(@source_dir + "/*.rb").each do |filename|
      method_sexp = get_method_sexp filename
      method_sexp.comments = nil
      method_code = Ruby2Ruby.new.process method_sexp
      f = open(@method_source_dir + "/" + File.basename(filename), "w+")
      f.write method_code
      f.close
    end
  end

  # Extract source of @method and its helper functions

  def build_whelper_source_dir
    FileUtils.mkdir @whelper_source_dir unless Dir.exists? @whelper_source_dir
    Dir.glob(@source_dir + "/*.rb").each do |filename|
      functions = get_all_functions filename
      method_sexp = get_method_sexp filename
      method_sexp.comments = nil
      method_code = Ruby2Ruby.new.process method_sexp
      tokens = Ripper.tokenize(method_code)
      f = open(@whelper_source_dir + "/" + File.basename(filename), "w+")
      functions.select { |func| tokens.include? func }.each do |func|
        func_sexp = get_method_sexp filename, func
        func_sexp.comments = nil
        f.write Ruby2Ruby.new.process func_sexp
        f.write "\n\n"
      end
    end
  end

  def get_source_dir
    @helper_flag ? @whelper_source_dir : @method_source_dir
  end

  def generate_library_call_feature
    output_dir = @feature_dir + "/library_call" 
    FileUtils.mkdir_p output_dir unless Dir.exists? output_dir
    Dir.glob(get_source_dir + "/*.rb").each do |filename|
      /.*\/(.*)\.rb$/ =~ filename
      f = open(output_dir + "/#{$1.to_s}.json", "w+")
      f.write count_library_call(filename).to_json
      f.close
    end
  end

  # Return a hash {function_name => # of calls}
  # TODO also count for helper function

  def count_library_call filename
    library_functions = [].to_set
    ["String", [], {}, //].each { |category| library_functions.merge category.methods}
    function_calls = {}
    recursive_count(RubyParser.new.process(IO.read(filename)), function_calls)
    function_calls.keep_if {|key, value| library_functions.include? key.to_sym}
  end

  # Helper function for count_calls

  def recursive_count sexp, output={}
    if sexp.class != Sexp.new.class
      return
    end
    if sexp[0] == :call
      if output[sexp[2].to_s]
        output[sexp[2].to_s] += 1
      else
        output[sexp[2].to_s] = 1
      end
    end
    sexp.each { |sub_sexp| recursive_count(sub_sexp, output)}   
  end 
  
  def generate_control_flow_feature
    output_dir = @feature_dir + "/control_flow" 
    FileUtils.mkdir_p output_dir unless Dir.exists? output_dir
    Dir.glob(get_source_dir + "/*.rb").each do |filename|
      /.*\/(.*)\.rb$/ =~ filename
      File.open(output_dir + "/#{$1.to_s}.json", "w+") do |f|
        f.puts count_keyword(filename).to_json
      end
    end
  end

  # Count keywords for a source file
  # Return a hash { keyword => # of appearance }

  def count_keyword filename
    hash = Ripper.tokenize(IO.read(filename)).select { |token| @control_flow_keywords.include? token }.group_by { |token| token }
    hash.each_pair do |key, value|
      hash[key] = value.length
    end
  end

  # Count length of @method for all rb files under source_dir
  # Generate a json file

  def generate_method_length_feature
    hash = {}
    Dir.glob(get_source_dir + "/*.rb").each do |filename|
      hash[File.basename(filename)] = IO.read(filename).lines.count 
    end
    f = open(@feature_dir + "/method_length.json", "w+")
    f.puts hash.to_json
    f.close
  end

  # Generate a json file for reek results of all submissions

  def generate_reek_feature
    warnings = []
    # Get reek warnings by reek api
    Dir.glob(get_source_dir + "/*.rb").each_with_index do |filename, i|
      # Examiner initialization takes an array
      warnings += Reek::Examiner.new([filename]).all_active_smells 
      puts "reek processing: " + i.to_s + " sources inspected" if i % 50 == 0
    end

    f = open(@feature_dir + "/reek.json", "w+")
    f.puts parse_warnings(warnings, @all_smells - @filter_smells).to_json
    f.close
  end

  # Helper function which return a hash with following format
  # { source_path => { warning1 => # of warning1, warning2 => # of warning2, ...} }

  def parse_warnings warnings, smells
    output_hash = {}

    warnings.group_by { |w| w.location["source"]}.each do |key, value|
      # Select warnings whose subclass belongs to smells and group them by their subclass
      subclass_to_ws = value.select { |w| smells.include? w.smell["subclass"]}.group_by { |w| w.smell["subclass"]}  
      subclass_to_num = {}
      subclass_to_ws.each { |subclass, ws| subclass_to_num[subclass] = ws.length }
      output_hash[File.basename(key)] = subclass_to_num
    end  
    return output_hash
  end

  def generate_flog_feature 
    output_dir = @feature_dir + "/flog"
    FileUtils.mkdir_p output_dir unless Dir.exists? output_dir
    Dir.glob(@source_dir + "/*.rb").each_with_index do |filename, i|
      /.*\/(.*)\.rb/ =~ filename
      f = open(output_dir + "/#{$1.to_s}_flog", "w+")
      f.puts `flog -am #{filename}`
      f.close
      puts "flog processing: " + i.to_s + " output files generated" if i%50==0
    end
  end

  def get_flog_feature
    flog_feature = Array.new(@indexes.length, 0)
    increment_flog_feature = Array.new(@indexes.length, 0)
    @indexes.each_with_index do |index, i|
      clean_code = IO.read(@method_source_dir + "/#{index.to_s}.rb")
      tokens = Ripper.tokenize(clean_code)
      flog_lines = IO.readlines(@feature_dir + "/flog/#{index.to_s}_flog")
      flog_lines.each do |line|
        /^\s*([^:]+): [^#]+#([^\s]+) .*/ =~ line
        if $2 == @method 
          flog_feature[i] = $1.to_f
        elsif tokens.include? $2
          increment_flog_feature[i] += $1.to_f 
        end
      end 
      puts "[ERROR] No #{@method} (in get_flog_feature): " + index.to_s if flog_feature[i] == 0
      flog_feature[i] += increment_flog_feature[i]
    end
    puts "--- #{increment_flog_feature.select { |f| f > 0}.length} subsmissions have helper function! ---" 
    write_column_feature_to_file increment_flog_feature, @feature_dir + "/increment_flog_feature.np"
    flog_feature
  end

  # This function can only be called after generated_reek_feature
  def get_reek_feature 
    hash = JSON.parse(IO.read(@feature_dir + "/reek.json"))
    smells = @all_smells - @filter_smells
    reek_feature = Array.new(@indexes.length) { Array.new(smells.length, 0)}
    
    @indexes.each_with_index do |index, i|
      smells.each_with_index do |smell, j|
        break unless hash[index.to_s + ".rb"] # some submission has no warning
        if hash[index.to_s + ".rb"][smell]
          reek_feature[i][j] = hash[index.to_s + ".rb"][smell]
        end
      end  
    end

    return reek_feature 
  end

  # This function can only be called after generate_reek_feature
  def generate_reek_count_feature
    hash = JSON.parse(IO.read(@feature_dir + "/reek.json"))
    smells = @all_smells - @filter_smells
    reek_count_feature = Array.new(@indexes.length, 0)
    @indexes.each_with_index do |index, i|
      next unless hash[index.to_s + ".rb"]
      reek_count_feature[i] = hash[index.to_s + ".rb"].values.reduce(0) {|accum, i| accum + i}
    end
    write_column_feature_to_file reek_count_feature, @feature_dir + "/reek_count_feature.np"
  end

  # This function can only be called after generate_library_call_feature
  # Return a list of all library functions called

  def get_library_functions_list
    library_functions = []
    @indexes.each do |index|
      library_functions += JSON.parse(IO.read(@feature_dir + "/library_calls/#{index.to_s}.json")).keys
    end
    library_functions.uniq
  end

  # This function can only be called after generate_library_call_feature

  def get_library_call_feature
    library_functions = get_library_functions_list
    puts "[# of all library functions called]: " + library_functions.length.to_s
    
    library_call_feature = Array.new(@indexes.length) { Array.new(library_functions.length, 0)}
    
    @indexes.each_with_index do |index, i|
      # Read JSON data for ith submission
      hash = JSON.parse(IO.read(@feature_dir + "/library_calls/#{index.to_s}.json"))
      library_functions.each_with_index do |function, j|
        if hash[function]
          library_call_feature[i][j] = hash[function]
        end
      end
    end
    return library_call_feature
  end

  # This function can only be called after generate_library_call_feature
  def generate_library_call_count_feature
    library_call_count_feature = Array.new(@indexes.length, 0)
    @indexes.each_with_index do |index, i|
      hash = JSON.parse(IO.read(@feature_dir + "/library_calls/#{index.to_s}.json"))
      library_call_count_feature[i] = hash.length
    end
    write_column_feature_to_file library_call_count_feature, "library_call_count_feature.np"
  end

  # This function can only be called after generate_control_flow_feature
  def get_control_flow_feature
    control_flow_feature = Array.new(@indexes.length) {Array.new(@control_flow_keywords.length, 0)}
    @indexes.each_with_index do |index, i|
      hash = JSON.parse(IO.read(@feature_dir + "/control_flow/#{index.to_s}.json"))
      @control_flow_keywords.each_with_index do |k, j|
        if hash[k]
          control_flow_feature[i][j] = hash[k]
        end
      end
    end
    return control_flow_feature
  end

  # This function can only be called after generate_control_flow_feature
  def generate_control_flow_count_feature
    control_flow_count_feature = Array.new(@indexes.length, 0) 
    @indexes.each_with_index do |index, i|
      hash = JSON.parse(IO.read(@feature_dir + "/control_flow/#{index.to_s}.json"))
      control_flow_count_feature[i] = hash.values.reduce(0) { |accum, i| accum + i}
    end
    write_column_feature_to_file control_flow_count_feature, "control_flow_count_feature.np"
  end

  # This function can only be called after generate_method_length_feature
  def get_method_length_feature
    hash = JSON.parse(IO.read(@feature_dir + "/method_length.json"))
    method_length_feature = []
    @indexes.each_with_index do |index, i|
      method_length_feature[i] = hash[index.to_s + ".rb"]
    end
    return method_length_feature
  end

  def write_column_feature_to_file feature, filename
    f = open(filename, "w+")
    0.upto(feature.length-1) do |i|
      f.write feature[i].to_f
      if i < (feature.length-1)
        f.write "\n"
      end
    end
    f.close
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

  def generate_saikuro_feature
    output_dir = @feature_dir + "/saikuro" 
    FileUtils.mkdir_p output_dir unless Dir.exists? output_dir
    Dir.glob(@clean_source_dir + "/*.rb").each_with_index do |filename, i|
      `~/CS169_Clustering/saikuro/bin/saikuro -c -p #{filename} -o #{output_dir}` 
      puts "saikuro processing: " + i.to_s + " reports generated" if i % 50 == 0
    end
  end

end

fg = FeatureGenerator.new(ARGV)
fg.featurize
