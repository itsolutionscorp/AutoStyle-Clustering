class Complement
  DNA_COMPLEMENTS = {
      'C' => 'G',
      'G' => 'C',
      'T' => 'A',
      'A' => 'U'
  }

  RNA_COMPLEMENTS = {
      'G' => 'C',
      'C' => 'G',
      'U' => 'A',
      'A' => 'T'
  }

  def self.of_dna(dna_strand)
    calculate_complement(dna_strand, DNA_COMPLEMENTS)
  end

  def self.of_rna(rna_strand)
    calculate_complement(rna_strand, RNA_COMPLEMENTS)
  end

  private

  def self.calculate_complement(strand, complements)
    complement_strand = ""
    strand.each_char do |nucleotide|
      complement_strand << complements[nucleotide]
    end
    complement_strand
  end
end
