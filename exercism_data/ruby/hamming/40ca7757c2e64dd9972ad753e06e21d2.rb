class Hamming
  def self.compute(strand_1, strand_2)
    strand_1.length.times.inject(0) do |hamming_distance, index|
      hamming_distance + (strand_1[index] == strand_2[index] ? 0 : 1)
    end
  end
end
