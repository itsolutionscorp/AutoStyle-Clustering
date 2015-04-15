class Grains
  
  def initialize
    x = 1 #This problem requires a starting value of 1
    @squares = *(1..64) #64 squares on a chess board
    @squares.map! { |a| 2**(a-1) *x }
  end
  
  def square(x)
    @squares[x-1]
  end
  
  def total
    @squares.inject(:+)
  end
end
