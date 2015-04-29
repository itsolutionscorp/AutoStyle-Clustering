class Hamming
	def self.compute(strand1, strand2)
		new.compute strand1, strand2
	end

	def compute(strand1, strand2)
		if strands_are_equivalent? strand1, strand2
			0	
		else
			compute_hamming_difference strand1, strand2
		end
	end

	private
	def compute_hamming_difference(strand1, strand2)
		diffCount = 0
		sArray1, sArray2 = to_char_array(strand1), to_char_array(strand2)

		sArray1.each_index do |i|
			diffCount+=1 if !sArray2[i].nil? &&
			 sArray1[i] != sArray2[i]
		end

		diffCount
	end

	def strands_are_equivalent?(strand1, strand2)
		strand1 == strand2
	end

	def to_char_array(strand)
		strand.split(//)	
	end
end
