class Strand
	attr_reader :strand

	def initialize(strand)
		@strand = strand.split ''
	end

	def length
		@strand.length
	end

	def -(other)
		difference = 0
		cut = [@strand.length,other.strand.length].min - 1
		strand1,strand2 = @strand[0..cut], other.strand[0..cut]

		for nucleobase in 0..cut
			difference +=1  if strand1[nucleobase] != strand2[nucleobase]
		end
		difference

	end

end

class Hamming
	def self.compute(strand1,strand2)
		Strand.new(strand1) - Strand.new(strand2)
	end
end
