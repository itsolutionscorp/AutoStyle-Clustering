# Author: patsul12
# First working attempt at this, wuld like feedback on how to make this code more concise

class Complement
	def self.of_dna(dna_string) 
		output = ""
		dna_string.split("").each do |c|
			if c == "C"
				c.gsub!("C", "G")
			elsif c == "G"
				c.gsub!("G", "C")
			elsif c == "T"
				c.gsub!("T", "A")
			elsif c == "A"
				c.gsub!("A", "U")
			end
			output << c
		end
		return output
	end

	def self.of_rna(rna_string) 
		output = ""
		rna_string.split("").each do |c|
			if c == "C"
				c.gsub!("C", "G")
			elsif c == "G"
				c.gsub!("G", "C")
			elsif c == "U"
				c.gsub!("U", "A")
			elsif c == "A"
				c.gsub!("A", "T")
			end
			output << c
		end
		return output
	end
end
