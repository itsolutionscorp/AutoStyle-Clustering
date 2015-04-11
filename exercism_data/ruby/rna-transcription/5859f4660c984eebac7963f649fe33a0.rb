class Complement
  @dna_to_rna = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(strand)
    strand.chars.map { |nucleotide| @dna_to_rna[nucleotide] }.join
  end

  def self.of_rna(strand)
    strand.chars.map { |nucleotide| @dna_to_rna.key(nucleotide) }.join
  end
end
