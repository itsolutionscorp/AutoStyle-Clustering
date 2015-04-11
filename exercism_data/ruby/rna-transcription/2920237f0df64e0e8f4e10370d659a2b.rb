class Complement
  COMPLEMENTS_OF_DNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  COMPLEMENTS_OF_RNA = COMPLEMENTS_OF_DNA.invert

  def self.of_dna(dna_strand)
    dna_strand.chars.map { |nucleotide| COMPLEMENTS_OF_DNA[nucleotide] }.join
  end

  def self.of_rna(rna_strand)
    rna_strand.chars.map { |nucleotide| COMPLEMENTS_OF_RNA[nucleotide] }.join
  end

end
