class Hamming

  def self.compute(left,right)
    (0...left.size).count {|i| different?(left[i],right[i]) }
  end

  def self.different?(left,right)
    left != right
  end

end
