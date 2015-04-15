class Hamming
  def self.compute(strand_a, strand_b)
    distance = 0

    strand_a.length.times do |i|
      distance += 1 unless strand_a[i] == strand_b[i]
    end
    distance
  end
end
