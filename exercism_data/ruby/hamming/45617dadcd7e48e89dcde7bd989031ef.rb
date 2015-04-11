class Hamming
  def self.compute(strand_1, strand_2)
    strand_1.chars.zip(strand_2.chars).inject(0) do |sum, pair|
      sum += 1 if pair[0] != pair[1]
      sum
    end
  end
end
