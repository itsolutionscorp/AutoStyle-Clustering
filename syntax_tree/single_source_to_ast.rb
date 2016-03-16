#! /usr/bin/env ruby
require 'ruby_parser'

def parse_source_into_ast(assignment_dir,filename)
    code = IO.read File.join(assignment_dir, "src", filename)
    ast = RubyParser.new.process code
    # File.open File.join(assignment_dir, "ast" , filename + ".ast"), "w+" do |f|
    #     f.write ast
    #     f.close
    # end
    puts ast
end

parse_source_into_ast(ARGV[0], ARGV[1])
