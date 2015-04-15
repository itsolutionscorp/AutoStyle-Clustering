def compute(strand1, strand2)
      min_length = [strand1.length, strand2.length].min
      strands_pair = strand1.chars.take(min_length).zip(strand2.chars)
      strands_pair.count { |nucleotide1, nucleotide2| nucleotide1 != nucleotide2 }
    end
  end