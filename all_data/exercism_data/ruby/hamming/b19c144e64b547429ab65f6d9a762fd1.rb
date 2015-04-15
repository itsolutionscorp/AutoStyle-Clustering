class Hamming
  def self.compute(one, two)
    one = one.split('')
    two = two.split('')
    one.zip(two).count  {|x, y| x != y }  
  end
end
