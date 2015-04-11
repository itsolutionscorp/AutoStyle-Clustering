class Hamming
  def self.compute(strand_a, strand_b)
    distance = 0
    strand_a.split('').each_with_index do |nuc, index|
      distance += 1 unless nuc == strand_b[index]
    end
    distance
  end
end
