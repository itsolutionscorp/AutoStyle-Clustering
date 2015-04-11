class Hamming
  def self.compute(a, b)
    strand_a = []
    strand_b = []
    @h_distance = 0

    a.each_char {|i| strand_a << i}
    b.each_char {|i| strand_b << i}

    if strand_a.length < strand_b.length
      self.compare(strand_a, strand_b)
    else
      self.compare(strand_b, strand_a)
    end

    @h_distance
  end

  def self.compare (m, n)
    m.each_with_index do |x, y|
      if x != n[y]
        @h_distance += 1
      end
    end
  end
end
