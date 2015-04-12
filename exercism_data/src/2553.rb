class Hamming
  def compute(strand_a, strand_b)
    if strand_a == strand_b
      0
    else
      short_a = strand_a.chars.take(strand_b.length)
      short_a.zip(strand_b.chars).count do |pair|
        pair[0] != pair[1]
      end
    end
  end
end
