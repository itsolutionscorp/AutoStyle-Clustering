class Grains
  def initialize
    @squares = (1..64).map {|s| 2**(s-1)}
  end

  def square(index)
    @squares[index-1]
  end

  def total
    @squares.reduce(:+)
  end
end
