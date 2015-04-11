class Complement
	@@dna_to_rna = {
			"G" => "C",
			"C" => "G",
			"T" => "A",
			"A" => "U"
		}

	def self.of_dna(strand)
		strand_complement(strand, @@dna_to_rna)
	end

	def self.of_rna(strand)
		strand_complement(strand, @@dna_to_rna.invert)
	end

  def self.strand_complement(strand, translation_table)
		strand.chars.reduce('') { |memo, nucleotide| memo << translation_table[nucleotide] }
	end
end
