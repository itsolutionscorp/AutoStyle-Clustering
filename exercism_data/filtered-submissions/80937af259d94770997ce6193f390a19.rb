def compute(strand, other_strand)
    strand = strand.chars.take(other_strand.length)
    other_strand = other_strand.chars

    pairs = strand.zip(other_strand)

    pairs.count do |left, right|
      left != right
    end
  end