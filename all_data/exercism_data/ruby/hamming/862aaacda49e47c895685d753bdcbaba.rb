class Hamming
  def self.compute(a, b)
    pairs = a.chars.zip(b.chars)
    pairs.count {|pair| pair[0] != pair[1]}
  end
end
