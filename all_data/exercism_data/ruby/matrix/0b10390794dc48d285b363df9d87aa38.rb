#!/usr/bin/env ruby
require 'rubygems'

class Matrix

	attr_accessor :rows, :columns

	public

	def initialize(arr)
		@rows = []
		@columns = []
		lines = arr.split("\n")
		lines.each do |line|
			create_row line
			create_columns line
		end
	end

	private 

	def create_row(line)
		@rows.push(line.split(" ").collect { |e| e.to_i } )
	end

	def create_columns(line)
		row_elements = line.split(" ").collect { |e| e.to_i }
		init_columns row_elements.count if @columns == []
		row_elements.each_with_index do |element, i|
			@columns[i].push element
		end
	end

	def init_columns size
		(0...size).each{ @columns.push [] }
	end

end
