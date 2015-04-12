class Hamming 
	def compute(first_dna, second_dna)
		first_arr = (first_dna.length > second_dna.length) ?  second_dna.split("") : first_dna.split("")
		first_arr.each_with_index.inject(0) do |acc, (value, index)|
			if !(first_dna[index] == second_dna[index])
				acc += 1
			end
		acc
		end
	end
end
