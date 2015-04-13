def compute(strand, other_strand)
    strand.chars.zip(other_strand.chars).select do |pair|
      pair.uniq.size > 1
    end.count
  end