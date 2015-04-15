class Hamming
  def self.compute(left, right)
    left.chars.zip(right.chars).select{|e| e[0] != e[1]}.count
  end
end
