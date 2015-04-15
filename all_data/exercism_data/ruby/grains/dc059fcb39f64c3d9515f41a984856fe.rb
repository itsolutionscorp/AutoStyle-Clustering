class Grains
  attr_accessor :array_of_grains
  def initialize
    @array_of_grains = []
    (0..63).map {|x| array_of_grains << 2**x}
  end
  
  def square(n)
    array_of_grains[n-1]
  end
  
  def total
    array_of_grains.reduce(:+)
  end
end
