class Complement
  @dna_complements = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  @rna_complements = @dna_complements.invert

  def self.of_dna(dna)
    dna.chars.map { |nucleotide| @dna_complements[nucleotide] }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |nucleotide| @rna_complements[nucleotide] }.join
  end
end
