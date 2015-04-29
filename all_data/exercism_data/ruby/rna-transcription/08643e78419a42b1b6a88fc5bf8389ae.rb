class Complement
	def self.of_dna(strand_to_check)
		strand_complemented = []
		if strand_to_check.empty? == false
			strand = strand_to_check.each_char.to_a
			for i in 0..strand.size
				if strand[i] == 'C'
					strand_complemented.push('G')
				end
				if strand[i] == 'G'
					strand_complemented.push('C')
				end
				if strand[i] == 'T'
					strand_complemented.push('A')
				end
				if strand[i] == 'A'
					strand_complemented.push('U')
				end
			end
		end
		strand_complemented.join
		end

	def self.of_rna(strand_to_check)
	strand_complemented = []
	if strand_to_check.empty? == false
		strand = strand_to_check.each_char.to_a
		for i in 0..strand.size
			if strand[i] == 'C'
				strand_complemented.push('G')
			end
			if strand[i] == 'G'
				strand_complemented.push('C')
			end
			if strand[i] == 'A'
				strand_complemented.push('T')
			end
			if strand[i] == 'U'
				strand_complemented.push('A')
			end
		end
	end
	strand_complemented.join
	end	
end



