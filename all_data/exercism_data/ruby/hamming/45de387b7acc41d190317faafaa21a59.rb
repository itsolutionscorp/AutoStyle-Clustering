class DNA
  DNA_NUCLEOTIDES = ['G', 'T', 'C', 'A']
  def initialize(nucleotides)
    @nucleotides = nucleotides
  end
  
  def hamming_distance(test_nucleotides)
    distance = 0
    @nucleotides[0...test_nucleotides.length].chars.zip(test_nucleotides.chars){|nucleotide_pair| distance += (nucleotide_pair.count(nucleotide_pair[0]) % 2)}
    distance
  end
end
