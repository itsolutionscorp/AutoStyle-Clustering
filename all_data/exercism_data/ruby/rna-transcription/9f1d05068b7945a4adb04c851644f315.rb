class Complement

	def self.of_dna(string)
		string.upcase!
		string.gsub!(/[GCTA]/, 'G'=> 'C','C'=>'G','T'=>'A','A'=>'U')
	end

	def self.of_rna(string)
		string.upcase!
		string.gsub!(/[CGAU]/, 'C'=> 'G','G'=>'C','A'=>'T','U'=>'A')
	end

end
