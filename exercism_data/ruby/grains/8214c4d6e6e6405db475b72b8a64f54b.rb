class Grains
  
  def initialize
    @squares = *(1..64)
    @squares.map! { |a| 2**(a-1) }
  end
  
  def square(x)
    @squares[x-1]
  end
  
  def total
    @squares.inject(:+)
  end
end
