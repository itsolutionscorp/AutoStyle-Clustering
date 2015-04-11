class Hamming
  def self.compute(strand_a, strand_b)
    strand_a.chars.zip(strand_b.chars).count do |arr|
      arr[0] != arr[1]
    end
  end
end
