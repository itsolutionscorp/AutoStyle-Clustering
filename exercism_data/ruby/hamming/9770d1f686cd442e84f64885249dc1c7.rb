class Hamming

  def self.compute(left,right)
    counter = 0
    (0...left.size).count {|i| same?(left[i],right[i]) }
    counter
  end

  def self.same?(left,right)
    left == right
  end

end
