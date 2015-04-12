def compute(strand1, strand2)
    strand1 = strand1.chars
    strand2 = strand2.chars

    distance = 0
    strand1.each_with_index do |nucleotide, index|
      return 1 if strand2[index] == nil
      distance += 1 if nucleotide != strand2[index]
    end
    distance
  end