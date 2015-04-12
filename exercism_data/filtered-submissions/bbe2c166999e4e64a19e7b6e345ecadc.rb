#! /usr/bin/env ruby
require 'rubygems'

class Hamming

	def compute(strand1,strand2)
		_strand1 = strand1.split("")
		_strand2 = strand2.split("")
		hams = 0
		_strand1.each_with_index do |s1, i|
			hams += 1 unless s1 == _strand2[i]
		end
		hams
	end

end
