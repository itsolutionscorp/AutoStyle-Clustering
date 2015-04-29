class Complement

  # keys are DNA, values are RNA complements
  NUCLEOTIDES = {
    G: :C,
    C: :G,
    T: :A,
    A: :U
  }

  # Convert DNA input to RNA output
  def self.of_dna(strand)
    strand.chars.map { |c| NUCLEOTIDES[c.upcase.to_sym] }.join
  end

  # Convert RNA input to DNA output
  def self.of_rna(strand)
    strand.chars.map { |c| NUCLEOTIDES.invert[c.upcase.to_sym] }.join
  end
end
