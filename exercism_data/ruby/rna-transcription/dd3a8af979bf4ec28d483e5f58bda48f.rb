def single_base_notA(char)
	if char == 'G'
		'C'
	elsif char == 'C'
		'G'
	elsif char == 'T'
		'A'
	elsif char == 'U'
		'A'
	end
end

class Complement

	def self.of_dna(strand)
		strand_array = strand.split('')
		complement = []
		strand_array.each_with_index do |char, i|
			if char == 'A'
				complement[i] = 'U'
			else
				complement[i] = single_base_notA(char)
			end
		end
		complement.join
	end

	def self.of_rna(strand)
		strand_array = strand.split('')
		complement = []
		strand_array.each_with_index do |char, i|
			if char == 'A'
				complement[i] = 'T'
			else
				complement[i] = single_base_notA(char)
			end
		end
		complement.join
	end

end
