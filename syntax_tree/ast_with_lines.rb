#!/usr/bin/env ruby
require 'yard'
require 'optparse'
require 'json'

LINE_DELIMITER = '::'

def print_tree filename, function_name
	code = IO.read filename
	begin
    	parser = YARD::Parser::Ruby::RubyParser.parse code
    	puts get_root_sexp(parser.ast, function_name)
	rescue YARD::Parser::ParserSyntaxError => e
		puts '( root ( ' + function_name + ' ) )'
	end
    
end

def get_root_sexp ast, function_name
	sexp = '( root ( ' + function_name + ' '
	ast.children.each do |child|
		sexp += get_sexp child
	end
	sexp += ') )'
end

def get_sexp ast
	sexp = ''
	if ast.call?
		sexp = '( ' + ast.method_name.source + LINE_DELIMITER + ast.line.to_s + ' '
		ast.children.each do |child|
			sexp += get_sexp child
		end
		sexp += ') '
	elsif ast.loop?
		sexp = '( iter' + LINE_DELIMITER + ast.line.to_s + ' '
		ast.children.each do |child|
			sexp += get_sexp child
		end
		sexp += ') '
	elsif ast.condition?
		sexp = '( cond' + LINE_DELIMITER + ast.line.to_s + ' '
		ast.children.each do |child|
			sexp += get_sexp child
		end
		sexp += ') '
	else
		ast.children.each do |child|
			sexp += get_sexp child
		end
	end
	sexp
end

print_tree(ARGV[0], ARGV[1])