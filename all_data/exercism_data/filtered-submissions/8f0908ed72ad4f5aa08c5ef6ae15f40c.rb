def compute(base_strand, descendent_strand)
    base_sequence = base_strand.chars
    descendent_sequence = descendent_strand.chars
    delta = 0

    base_sequence.each_with_index { |nucleotide, index|
      next if !descendent_sequence[index]
      delta +=1 if nucleotide != descendent_sequence[index]
    }

    return delta
  end