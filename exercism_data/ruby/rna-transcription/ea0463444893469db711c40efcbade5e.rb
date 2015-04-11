class Complement	
	def self.of_dna(dna)
		dna = dna.split("")
		dna.each do |x|
			if x == "G"
				x.replace("C")
			elsif x == "C"
				x.replace("G")
			elsif x == "T"
				x.replace("A")
			elsif x == "A"
				x.replace("U")
			else
				x
			end
		end
		dna.join
	end

	def self.of_rna(rna)
		rna = rna.split("")
		rna.each do |x|
			if x == "C"
				x.replace("G")
			elsif x == "G"
				x.replace("C")
			elsif x == "A"
				x.replace("T")
			elsif x == "U"
				x.replace("A")
			else
				x
			end
		end
		rna.join
	end
end
