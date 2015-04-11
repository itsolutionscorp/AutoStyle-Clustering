class Hamming
  def self.compute(strand_a, strand_b)
    strand_b = strand_b.chars
    strand_a.chars.each_with_index.inject(0) do |sum, (value, i)|
      return sum if !strand_b[i]
      sum += 1 if value != strand_b[i]
      sum
    end
  end
end
