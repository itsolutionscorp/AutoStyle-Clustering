class Hamming
	
	def compute dna_a, dna_b
		dna_a.chars.select.with_index{ |base, index| 
				base != dna_b[index]
			}
			.count
	end
	
end
