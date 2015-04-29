class DNA
  def initialize(strand)
    @strand = normalize(strand)
  end

  def hamming_distance(strand2)
    strand2 = normalize(strand2)

    distance = 0

    @strand.each_with_index { |nucleotide, index|
      distance += 1 unless strand2[index] == nucleotide
    }

    distance
  end

  private 
  def normalize(input)
    input.split('')
  end
end
