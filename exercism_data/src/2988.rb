class Hamming

	def compute(strand_a, strand_b)
		count = 0
		strand_a.split("").each_with_index do |nuc_a, index|
			nuc_b = strand_b[index]
			unless nuc_a == nuc_b
				count +=1
			end
		end
		
		count
	end

end
