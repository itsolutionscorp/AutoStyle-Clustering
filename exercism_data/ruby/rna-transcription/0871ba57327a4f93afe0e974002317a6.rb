class Complement
	DNA_COMPLEMENTS = {G: 'C', C: 'G', T: 'A', A: 'U'}

	def self.of_dna(nucleotide)
		complement_string = ''
		nucleotide.each_char {|char|
			complement_string << DNA_COMPLEMENTS[char.to_sym]
		}
		return complement_string
	end

	def self.of_rna(nucleotide)
		complement_string = ''
		nucleotide.each_char {|char|
			complement_string << DNA_COMPLEMENTS.key(char).to_s
		}
		return complement_string
	end
end
