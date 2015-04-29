class Complement
  RNA_COMPLEMENTS_MAP = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  DNA_COMPLEMENTS_MAP = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}

	def self.of_dna(dna)
    complements(dna, RNA_COMPLEMENTS_MAP)
	end

  def self.of_rna(rna)
    complements(rna, DNA_COMPLEMENTS_MAP)
  end

  private

  def self.complements(nucleotides, complement_map)
    nucleotides.chars.each_with_object('') do |nucleotide, complements|
      complements << complement_map[nucleotide]
    end
  end
end
