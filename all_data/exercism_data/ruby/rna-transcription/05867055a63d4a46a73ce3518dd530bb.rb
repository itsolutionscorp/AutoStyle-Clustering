class Complement
	@@dna = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
	@@rna = {"C" => "G", "G" => "C", "A" => "T",  "U" => "A"}

	def self.of_dna(s)
		translate(s, @@dna)
	end 

	def self.of_rna(s)
		translate(s, @@rna)
	end 

	def self.translate(s, h)
		s.split(//).inject('') {|result, character| 
			result + h[character]}
	end

end
