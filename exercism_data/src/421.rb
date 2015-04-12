def compute strand_a, strand_b
    raise ArgumentError, "Strands must be the same length" unless strand_a.length == strand_b.length
    return 0 if strand_a == strand_b
    count = 0
    strand_a.each_char.each_with_index do |nucleotide, index|
      count += 1 if nucleotide != strand_b[index]
    end
    count
  end