class Hamming
	

	def self.get_length_of_chains(first_chain, second_chain)
		[first_chain.length, second_chain.length].min
	end

	def self.compute(first_chain, second_chain)		
		length_of_chains = get_length_of_chains(first_chain, second_chain)	

		hamming_distance = 0
		current_nucleotide = 0
		while current_nucleotide < length_of_chains  do
			if (first_chain[current_nucleotide] != second_chain[current_nucleotide])
				hamming_distance += 1
			end
			current_nucleotide += 1
		end

		return hamming_distance
	end


end
