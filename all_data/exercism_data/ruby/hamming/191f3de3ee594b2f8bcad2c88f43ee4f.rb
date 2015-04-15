require 'set'

class Strand
	attr_reader :strand
	
	def valid?
		nucleobases = Set.new ['A','C','G','T']
		(Set.new(@strand) + nucleobases).size == nucleobases.size
	end

	def initialize(strand)
		@strand = strand.split ''
		if not valid?
			raise "Unknown nucleonbase(s) detected!"
		end
	end

	def -(other)
        	@strand.zip(other.strand).count { |a,b| (a != b) and !b.nil? }
	end
end

class Hamming
	def self.compute(strand1,strand2)
		Strand.new(strand1) - Strand.new(strand2)
	end

end
