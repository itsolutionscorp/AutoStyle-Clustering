class DNA
	def initialize(dna)
		@dna = dna
	end

	def hamming_distance(other_dna)
		if @dna == other_dna
			return 0
		else
			hamming_distance = 0
			count = 0
			while count < (other_dna.size < @dna.size ? other_dna.size : @dna.size)
				if @dna[count] != other_dna[count]
					hamming_distance += 1
				end
				count = count + 1
			end
		end
		return hamming_distance
	end
end
