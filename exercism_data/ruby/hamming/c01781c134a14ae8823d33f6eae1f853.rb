class Hamming

  def self.compute(strand_a, strand_b)
    compare_arrays(strand_a.chars, strand_b.chars)
  end

  private

  def self.compare_arrays(a, b)
    count = 0
    unless a == b
      [a.size, b.size].min.times do |i|
        count += 1 if a[i] != b[i]
      end
    end
    count
  end

end
