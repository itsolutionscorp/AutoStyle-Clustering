class Grains
  attr_accessor :array_of_grains
  def initialize
    @array_of_grains
  end
  
  def square(n)
    array_of_grains = []
    (0..63).each {|x| array_of_grains << 2**x}
    n = n-1
    array_of_grains[n]
  end
  
  def total
    array_of_grains.reduce(:+)
  end
  
end
