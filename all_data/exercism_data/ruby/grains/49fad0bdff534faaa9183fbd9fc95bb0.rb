class Grains

  def initialize
    @squares = []
    @squares[0] = 1
    (1..63).each {|x| @squares[x] = @squares[x-1] * 2 }
  end
 
  def square(sq)
    @squares[sq-1]
  end

  def total
    @squares.inject { |sum,x| sum + x }
  end

end
