class Complement 

	def self.of_dna(dna)
		dna.chars.map { |a| build_dna(a) }.join
	end

	def self.of_rna(rna)
		rna.chars.map { |a| build_rna(a) }.join
	end

	private
	def self.build_dna(char)
		case char
			when 'G' then 'C'
			when 'C' then 'G'
			when 'T' then 'A'
			when 'A' then 'U'
		end
	end

	def self.build_rna(char)
		case char
			when 'C' then 'G'
			when 'G' then 'C'
			when 'A' then 'T'
			when 'U' then 'A'
		end
	end
	

end
