#!/usr/bin/env ruby

class Complement
	def self.of_dna(rnastring)
		rnaarr = rnastring.chars
		#puts rnaarr
		dnastr = String.new

		rnaarr.each  do |i|
			case i
				when 'C'
					dnastr << 'G'
				when 'G'
					dnastr << 'C'
				when 'T'
					dnastr << 'A'
				when 'A'
					dnastr << 'U'
			end
		end
		#puts dnastr
		return dnastr
	end

	def self.of_rna(dnastring)
		dnaarr = dnastring.chars
		rnastr = String.new

		dnaarr.each  do |i|
			case i
				when 'C'
					rnastr << 'G'
				when 'G'
					rnastr << 'C'
				when 'A'
					rnastr << 'T'
				when 'U'
					rnastr << 'A'
			end
		end
		return rnastr
	end
end
