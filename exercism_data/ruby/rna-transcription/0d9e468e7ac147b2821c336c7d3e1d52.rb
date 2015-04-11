class Complement
	@@to_rna = { "G"=>"C", "C"=>"G", "T"=>"A", "A"=>"U"}
	@@to_dna = { "C"=>"G", "G"=>"C", "A"=>"T", "U"=>"A"}

	def self.of_dna(dna)
		rna = ""
		dna.each_char{|char| rna << @@to_rna[char]}
		rna
	end

	def self.of_rna(rna)
		dna = ""
		rna.each_char{|char| dna << @@to_dna[char]}
		dna
	end

end
