class Hamming
  def self.compute(strand1, strand2)
    base_pairs = strand1.chars.take(strand2.length).zip(strand2.chars)
    base_pairs.count do |base1, base2|
      base1 != base2
    end
  end
end
