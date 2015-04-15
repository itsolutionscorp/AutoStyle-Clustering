class Complement
	def self.of_dna(strand)
		strand_complement(strand, "DNA")
	end

	def self.of_rna(strand)
		strand_complement(strand, "RNA")
	end

  def self.strand_complement(strand, from)
		strand.chars.reduce('') { |memo, nucleotide| memo << nucleo_complement(nucleotide, from) }
	end

	def self.nucleo_complement(nucleotide, from)
		translation_table = {
			"G" => "C",
			"C" => "G",
			"T" => "A",
			"U" => "A",
			"A" => { "DNA" => "U", "RNA" => "T" }
		}
		return translation_table[nucleotide][from] if nucleotide == "A"
		translation_table[nucleotide]
	end
end
