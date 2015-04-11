class Hamming

	def self.compute(first_homologue, second_homologue)
		hamming_distance = 0
		first_homologue.chars.each_with_index do |nucleotide, index|
				hamming_distance += 1 if !second_homologue[index].nil? and nucleotide != second_homologue[index] 
		end 
		hamming_distance
	end

end
