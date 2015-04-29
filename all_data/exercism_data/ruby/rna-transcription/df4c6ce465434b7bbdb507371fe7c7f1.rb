class Complement

	def self.of_dna(dna)
		dna = dna.split("")	
		rna =""

		dna.each { |n| rna << {"A" => "U", "C" => "G", "G" => "C", "T" => "A"}[n]}
		rna
	end

	def self.of_rna(rna)
		rna = rna.split("")
		dna = ""
		
		rna.each { |n| dna << {"A" => "T", "G" => "C", "C" => "G", "U"=> "A"}[n] }
		dna
	end

end
