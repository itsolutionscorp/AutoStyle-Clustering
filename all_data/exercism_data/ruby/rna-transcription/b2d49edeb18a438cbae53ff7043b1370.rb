class Complement

	def self.of_dna(dna)
		h_dna = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
		i = 0
		while dna[i] do
			if h_dna.has_key?(dna[i])
				dna[i] = h_dna[dna[i]]
			end
			i += 1
		end
		return dna
	end

	def self.of_rna(rna)
		h_dna = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
		h_rna = h_dna.invert
		i = 0
		while rna[i] do
			if h_rna.has_key?(rna[i])
				rna[i] = h_rna[rna[i]]
			end
			i += 1
		end
		return rna
	end

end
