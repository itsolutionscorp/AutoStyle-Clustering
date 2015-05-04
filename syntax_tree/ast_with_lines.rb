#!/usr/bin/env ruby
require 'yard'
require 'optparse'
require 'json'

# Each node contains both a name and a line number that the thing in 
# the node appeared on in the source code. They are separated by 
# this thing.
LINE_DELIMITER = '::'

# Prints the syntax tree of the function in the file as an s-expression -- 
# that is, a tree represented as an expression with nested parentheses.

# If only_structure is not nil, then the syntax tree will only note
# where there are loops and conditionals. It will not contain nodes for
# library calls.

#In the case of a syntax error, it outputs a syntax tree that contains
# only the function name.
def print_tree filename, function_name, only_structure
    code = IO.read filename
    begin
        parser = YARD::Parser::Ruby::RubyParser.parse code
        puts get_root_sexp(parser.ast, function_name, only_structure)
    rescue YARD::Parser::ParserSyntaxError => e
        puts '( root ( ' + function_name + ' ) )'
    end 
end 

def get_root_sexp ast, function_name, only_structure
    sexp = '( root ( ' + function_name + ' '
    ast.children.each do |child|
        sexp += get_sexp(child, only_structure)
    end
    sexp += ') )'
end

# Rerturns a string representing the abstract syntax tree as an s-expression.
# This is a recursive function!
def get_sexp ast, only_structure
    sexp = ''
    if ast.call? and not only_structure
        sexp = '( ' + ast.method_name.source + LINE_DELIMITER + ast.line.to_s + ' '
        ast.children.each do |child|
            sexp += get_sexp(child, only_structure)
        end
        sexp += ') '
    elsif ast.loop?
        sexp = '( iter' + LINE_DELIMITER + ast.line.to_s + ' '
        ast.children.each do |child|
            sexp += get_sexp(child, only_structure)
        end
        sexp += ') '
    elsif ast.condition?
        sexp = '( cond' + LINE_DELIMITER + ast.line.to_s + ' '
        ast.children.each do |child|
            sexp += get_sexp(child, only_structure)
        end
        sexp += ') '
    else
        ast.children.each do |child|
            sexp += get_sexp(child, only_structure)
        end
    end
    sexp
end

file_name = ARGV.shift
function_name = ARGV.shift
only_structure = ARGV.shift #Will be nil if someone didn't add an extra option.
print_tree(file_name, function_name, only_structure)