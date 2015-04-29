#!/usr/bin/env ruby
require 'yard'
require 'optparse'
require 'json'

def featurize src_dir, out_file
    all_names_and_lines = []
    Dir.glob(src_dir + "/*.rb").sort_by{|f| f.split("/")[-1].split(".")[0].to_i }.each do |filename|
        print filename.split("/")[-1]
        code = IO.read filename
        parser = YARD::Parser::Ruby::RubyParser.parse code
        names_and_lines = featurize_tree parser.ast
        all_names_and_lines << names_and_lines
    end
    json_names_and_lines = JSON.generate(all_names_and_lines)
    File.write(out_file, json_names_and_lines)
end

def featurize_tree ast
    names_and_lines = Hash.new{|h,k| h[k] = []}
    stack = []
    stack << ast
    while !stack.empty? do
        current_node = stack.pop
        if current_node.call?
            method_name = current_node.method_name.source
            method_line = current_node.method_name.line
            if method_name == 'new'
                names_and_lines[method_name] << method_line
                method_name = current_node.children[0].source.downcase
            end
            names_and_lines[method_name] << method_line
        end
        if current_node.type == :assign
            if current_node.children[1].type == :hash
                names_and_lines['hash'] << current_node.line
            elsif current_node.children[1].type == :array
                names_and_lines['array'] << current_node.line
            end
        end
        current_node.children.each do |child|
            stack.push child
        end
    end
    names_and_lines
end
featurize(ARGV[0], ARGV[1])