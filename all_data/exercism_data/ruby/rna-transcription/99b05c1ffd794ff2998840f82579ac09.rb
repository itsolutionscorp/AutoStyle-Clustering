class Complement
	def self.of_dna(dna_strand)
		dna_to_rna = {"C" => "G", "G" => "C", "T" => "A", "A" => "U"}
		dna_strand.gsub(/[CGTA]/,dna_to_rna)
	end

	def self.of_rna(rna_strand)
		rna_to_dna = {"C" => "G", "G" => "C", "U" => "A", "A" => "T"}
		rna_strand.gsub(/[CGUA]/,rna_to_dna)
	end
end
