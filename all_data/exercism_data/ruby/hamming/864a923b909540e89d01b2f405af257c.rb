class Hamming 
	def self.compute(first_dna, second_dna)
		acc = 0
		first_arr = (first_dna.length > second_dna.length) ?  second_dna.split("") : first_dna.split("")
		first_arr.each_with_index do |value, index|
			if !(first_dna[index] == second_dna[index])
				acc += 1
			end
		end
		acc
	end
end
