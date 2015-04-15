class Hamming
  def self.compute(one, two)
    one = one.chars.zip(two.chars)
    one.count {|x, y| x != y }  
  end
end
