class DNA
  DNA_NUCLEOTIDES = ['G', 'T', 'C', 'A']
  def initialize(nucleotides)
    @nucleotides = nucleotides
  end
  
  def hamming_distance(test_nucleotides)
    distance = 0
    (0..(@nucleotides.length - 1)).each do |i|
      if (@nucleotides[i] != test_nucleotides[i]) && (@nucleotides[i] && test_nucleotides[i])
        distance += 1
      end
    end
    distance
  end
end
