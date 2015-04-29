class Complement
	@@dna = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
	@@rna = {"C" => "G", "G" => "C", "A" => "T",  "U" => "A"}

	def self.of_dna(s)
		a = s.split(//).map { |letter|
			letter = @@dna[letter]
		}
		a.join("")
	end 

	def self.of_rna(s)
		a = s.split(//).map { |letter|
			@@rna[letter] 
		}
		a.join("")
	end 


end
