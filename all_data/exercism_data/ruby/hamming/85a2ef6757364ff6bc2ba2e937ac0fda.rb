class Hamming
  def self.compute(a, b)
    return nil unless a.length == b.length

    pairs = a.chars.zip(b.chars)
    pairs.count {|pair| pair[0] != pair[1]}
  end
end
