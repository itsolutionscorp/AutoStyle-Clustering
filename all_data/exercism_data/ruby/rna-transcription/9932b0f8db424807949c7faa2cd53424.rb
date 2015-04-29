class Complement
  @complements = {
    G: 'C',
    C: 'G',
    T: 'A',
    A: 'U'
  }

  def self.of_dna(strand)
    strand.chars.map { |nucleotide| @complements[nucleotide.to_sym] }.join
  end

  def self.of_rna(strand)
    strand.chars.map { |nucleotide| @complements.key(nucleotide) }.join
  end
end
