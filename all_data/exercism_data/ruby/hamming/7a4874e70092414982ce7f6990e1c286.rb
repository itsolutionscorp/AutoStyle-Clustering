class DNA
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(input_strand)
    strand1, strand2 = trim_strands_to_equal_length(@strand, input_strand)
    calculate_distance(strand1, strand2)
  end

  private 
  def normalize(input)
    input.split('')
  end

  def trim_strands_to_equal_length(s1, s2)
    min_length = [s1.size, s2.size].min - 1
    [normalize(s1)[0..min_length], normalize(s2)[0..min_length]]
  end

  def calculate_distance(strand1, strand2)
    distance = 0

    strand1.each_with_index { |nucleotide, index|
      distance += 1 unless strand2[index] == nucleotide
    }

    distance
  end
end
