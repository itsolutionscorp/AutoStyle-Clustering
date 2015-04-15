module Complement
	Trans_dna = {"G"=>"C","C"=>"G","T"=>"A","A"=>"U"}
	Trans_rna = Trans_dna.invert
	def self.of_dna(dna)
		@rna = ""
		dna.each_char do |letter|
			@rna.concat(Trans_dna[letter])
		end
		return @rna
	end
	def self.of_rna(rna)
		@dna = ""
		rna.each_char do |letter|
			@dna.concat(Trans_rna[letter])
		end
		return @dna
	end
end
