class Complement
	def self.single_of_dna(nuc)
		case nuc
		when 'G'
			comp='C'
		when 'C'
			comp='G'
		when 'T'
			comp='A'
		when 'A'
			comp='U'
		end
		comp
	end

	def self.single_of_rna(nuc)
		case nuc
		when 'C'
			comp='G'
		when 'G'
			comp='C'
		when 'A'
			comp='T'
		when 'U'
			comp='A'
		end
		comp
	end

	def self.of_dna(dna)
		comp = "";
		dna.each_char{|i| comp += single_of_dna(i)}
		comp
	end
	def self.of_rna(dna)
		comp = "";
		dna.each_char{|i| comp += single_of_rna(i)}
		comp
	end
end
