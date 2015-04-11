class Complement
	def self.of_dna(dna)
		dna.gsub(/./,'G'=>'C','C'=>'G','T'=>'A','A'=>'U')
	end
	def self.of_rna(rna)
		rna.gsub(/./,'C'=>'G','G'=>'C','A'=>'T','U'=>'A')
	end
end
