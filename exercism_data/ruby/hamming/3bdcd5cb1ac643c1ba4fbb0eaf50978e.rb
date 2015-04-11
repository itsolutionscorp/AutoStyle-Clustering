class Hamming
  def self.compute(strand_a, strand_b)
    strand_b = strand_b.split('')
    strand_a.split('').each_with_index.inject(0) do |sum, (value, index)|
      return sum if !strand_b[index]
      sum += 1 if value != strand_b[index]
      sum
    end
  end
end
