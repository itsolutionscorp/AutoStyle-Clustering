class DNA
  attr_reader :nucleotides

  def initialize(dna)
    @nucleotides = dna
  end

  def hamming_distance(strand)
    nucleotide_comparison = nucleotides.chars.zip(strand.chars)
    find_mutations(nucleotide_comparison).length
  end

  def find_mutations(comparison)
    comparison.reject{|c1, c2| c1 == c2 || c2.nil?}
  end

end
