class Complement

  COMPLEMENTS = {
    G: :C,
    C: :G,
    T: :A,
    A: :U,
  }

  def self.of_dna(nucleotides)
    nucleotides.chars.map { |nucleotide| COMPLEMENTS[nucleotide.to_sym] }.join('')
  end

  def self.of_rna(nucleotides)
    nucleotides.chars.map { |nucleotide| COMPLEMENTS.invert[nucleotide.to_sym] }.join('')
  end

end
