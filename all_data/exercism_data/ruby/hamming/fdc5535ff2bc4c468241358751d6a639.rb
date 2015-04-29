class DNA
  DNA_NUCLEOTIDES = ['G', 'T', 'C', 'A']
  def initialize(nucleotides)
    @nucleotides = nucleotides.chars
  end
  
  def hamming_distance(test_nucleotides)
    nucleotide_pairs = @nucleotides.first(test_nucleotides.length).zip(test_nucleotides.chars)
    nucleotide_pairs.count {|first, second| first != second }
  end
end
