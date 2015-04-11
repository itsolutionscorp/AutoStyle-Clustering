class Complement
	def self.of_dna(rna)
		dna = ""
		translator = 'GCAT'
		rna.chars.each do |val|
			dna += 'CGUA'[translator.index(val)]
		end
		dna
	end

	def self.of_rna(dna)
		rna = ""
		translator = 'GCUA'
		dna.chars.each do |val|
			rna += 'CGAT'[translator.index(val)]
		end
		rna
	end
end
