class DNA
	def initialize(dna)
		@dna = dna
	end

	def hamming_distance(sequence)
		smallest = [@dna.chars.size, sequence.chars.size].min
		small = (@dna.chars.size == smallest) ? @dna.chars : sequence.chars
		large =  (@dna.chars.size == smallest) ? sequence.chars : @dna.chars
		hamming = 0

		small.each_with_index do |char, index|
			if large[index] != char
				hamming += 1
			end
		end
		return hamming
	end
end
