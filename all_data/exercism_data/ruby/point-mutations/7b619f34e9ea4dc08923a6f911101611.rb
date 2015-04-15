class DNA
  attr_reader :nucleotides

  def initialize(dna)
    @nucleotides = dna
  end

  def hamming_distance(strand)
    nucleotide_comparison = nucleotides.chars.zip(strand.chars)
    find_mutations(nucleotide_comparison)
  end

  def find_mutations(comparison)
    comparison.count{|c1, c2| c1 != c2 && c2 }
  end

end
