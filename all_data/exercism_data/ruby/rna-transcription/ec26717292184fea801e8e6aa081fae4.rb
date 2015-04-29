class Complement

	@@dna_to_rna = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
	@@rna_to_dna = Hash.new

	def self.dna_to_rna
		@@dna_to_rna
	end

	def self.rna_to_dna
		@@dna_to_rna.invert
	end

	def self.of_dna (dna_strand)
		return dna_strand.scan(/./).map{ |nucleotide| self.dna_to_rna[nucleotide]}.join("")
	end

	def self.of_rna (rna_strand)
		return rna_strand.scan(/./).map{ |nucleotide| self.rna_to_dna[nucleotide]}.join("")
	end

end
