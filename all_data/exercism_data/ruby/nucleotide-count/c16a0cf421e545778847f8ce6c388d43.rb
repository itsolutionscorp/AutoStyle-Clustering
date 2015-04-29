class Array
	def frequencies
		self.each_with_object(Hash.new(0)) do |key, hash|
			hash[key] += 1
		end
	end
end

class Nucleotide
	attr_reader :histogram
	
	EMPTY_HISTOGRAM = { "A" => 0, "C" => 0, "G" => 0, "T" => 0 }

	class << self
		def from_dna(strand)
			new(strand)
		end
	end
	
	def initialize(strand)
		raise ArgumentError unless strand.match(/[^ACGT]/).nil?
		@nucleotides = strand.upcase.chars
		@histogram = EMPTY_HISTOGRAM.merge(@nucleotides.frequencies)
	end
	
	def count(nucleotide)
		histogram[nucleotide]
	end
end
