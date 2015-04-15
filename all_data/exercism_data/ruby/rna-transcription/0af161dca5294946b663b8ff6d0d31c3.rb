class Complement
	def self.of_dna(rna)
		dna = ""
		translator = 'GCAT'
		rna.chars.each do |val|
			dna += 'CGUA'[translator.index(val)]
			#'GCAU' --> 'CGTA'
		end
		dna
	end

	def self.of_rna(dna)
		rna = ""
		translator = 'GCUA'
		dna.chars.each do |val|
			rna += 'CGAT'[translator.index(val)]
			#'GCAU' --> 'CGTA'
		end
		rna
	end
end
