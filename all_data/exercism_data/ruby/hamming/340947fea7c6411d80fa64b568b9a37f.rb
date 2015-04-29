class Strand
	attr_reader :strand

	def initialize(strand)
		@strand = strand.split(//)
	end

	def length
		@strand.length
	end

	def -(other)
		difference = 0
		if @strand.length < other.strand.length
			strand1 = @strand
			strand2 = other.strand
		else
			strand1 = other.strand
			strand2 = @strand
		end

		for index in 0..strand1.length - 1
			if strand1[index] != strand2[index]
				difference += 1
			end
		end
		difference

	end

end

class Hamming
	def self.compute(strand1,strand2)
		Strand.new(strand1) - Strand.new(strand2)
	end
end
