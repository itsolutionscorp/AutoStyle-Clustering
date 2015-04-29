class Complement

	def self.of_rna(rna)
		data = {"C" => "G",
					 "G" => "C",
					 "A" => "T",
					 "U" => "A"}
		array_of_rna = rna.split("")
		rna_to_dna = array_of_rna.map do |i|
			i = data[i]
		end
		rna_to_dna.join("")
	end

	def self.of_dna(dna)
		data = {"G" => "C",
					 "C" => "G",
					 "T" => "A",
					 "A" => "U"}
		array_of_dna = dna.split("")
		dna_to_rna = array_of_dna.map do |i|
			i = data[i]
		end
		dna_to_rna.join("")
	end

end
