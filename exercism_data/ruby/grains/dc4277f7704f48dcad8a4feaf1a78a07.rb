class Grains
  attr_accessor :total
  
  def initialize 
    @total = (1..64).inject { |sum, n| sum + square(n) }
  end
  
  def square(n)
    return (2**n)/2 if n.between?(1,64)
    0
  end

  def total
    @total
  end
end
