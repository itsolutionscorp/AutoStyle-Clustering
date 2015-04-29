def compute(strand_a, strand_b)
    if strand_a == strand_b
      0
    else
      short_a = strand_a.chars.take(strand_b.length)
      short_a.zip(strand_b.chars).reduce(0) do |result, pair|
        result += 1 unless pair[0] == pair[1]
        result
      end
    end
  end