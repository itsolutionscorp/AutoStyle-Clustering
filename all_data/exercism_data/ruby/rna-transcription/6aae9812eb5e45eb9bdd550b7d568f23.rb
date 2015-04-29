class Complement
	def self.of_dna(dna)
		rna = dna
		rna.gsub!(/./) do |gene|

			if gene.downcase == "g"
				gene = "C"
			elsif gene.downcase == "c"
				gene = "G"
			elsif gene.downcase == "t"
				gene = "A"
			else
				gene = "U"
			end
		end
		return rna
	end

	def self.of_rna(rna)
		dna = rna
		dna.gsub!(/./) do |gene|
			if gene.downcase == "c"
				gene = "G"
			elsif gene.downcase == "g"
				gene = "C"
			elsif gene.downcase == "a"
				gene = "T"
			else
				gene = "A"
			end
		end
		return dna
	end
end
