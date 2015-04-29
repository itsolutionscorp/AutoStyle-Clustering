class DNA
  DNA_NUCLEOTIDES = ['G', 'T', 'C', 'A']
  def initialize(nucleotides)
    @nucleotides = nucleotides.chars
  end
  
  def hamming_distance(test_nucleotides)
    distance = 0
    @nucleotides.each_with_index do |nucleotide, i|
      if (nucleotide != test_nucleotides[i]) && (test_nucleotides[i])
        distance += 1
      end
    end
    distance
  end
end
