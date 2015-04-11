class Hamming
  def self.compute(first,second)
    first.chars.zip(second.chars).count {|pair| pair.first != pair.last}
  end
end
