class Hamming
  def self.compute(strand1, strand2)
    pairs = strand1.chars.zip(strand2.chars)
    pairs.inject(0) do |count, pair|
      count = pair[1] && pair[0] != pair[1] ? count += 1 : count
    end
  end
end
