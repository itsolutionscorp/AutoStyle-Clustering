class Hamming
  def self.compute(strand_a, strand_b)
    @h_distance = 0

    if strand_a.length < strand_b.length
      self.compare(strand_a, strand_b)
    else
      self.compare(strand_b, strand_a)
    end

    @h_distance
  end

  def self.compare (m, n)
    0.upto(m.length - 1) do |i|
      if m[i] != n[i]
        @h_distance += 1
      end
    end
  end
end
