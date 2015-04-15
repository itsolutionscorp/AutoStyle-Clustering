class Complement
	def self.replace_character(character, o1, o2)
		mapping = [['G', 'C'], ['C', 'G'], ['T', 'A'], ['A', 'U']]
		mapping.each do | map |
			return map[o1] if character == map[o2]
		end
	end

	def self.of_dna(strand)
		dna_complete = []
		strand.each_char do | character |
			dna_complete << replace_character(character, 1, 0)
		end
		return dna_complete.join
	end

	def self.of_rna(strand)
		rna_complete = []
		strand.each_char do | character |
			rna_complete << replace_character(character, 0, 1)
		end
		return rna_complete.join
	end
end
