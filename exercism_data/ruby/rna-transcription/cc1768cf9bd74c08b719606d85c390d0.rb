class Complement

	def self.of_dna(dna)
		dna = dna.split("")	
		rna =""

		dna.each do |x|
			rna << "U" if x == "A" 
			rna << "G" if x == "C" 
			rna << "C" if x == "G"
			rna << "A" if x == "T" 
		end
		rna
	end

	def self.of_rna(rna)
		rna = rna.split("")	
		dna =""

		rna.each do |x|
			dna << "T" if x == "A" 
			dna << "G" if x == "C" 
			dna << "C" if x == "G"
			dna << "A" if x == "U" 
		end
		dna
	end

end
