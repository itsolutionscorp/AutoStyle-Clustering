class Grains

  TOTAL_SQUARES = 64

  def initialize(lazy=nil)
    @squares = {}
    return if lazy
    fill_squares(TOTAL_SQUARES)
  end

  def fill_squares(count)
    (1..count).each {|i| square(i)}
    self
  end

  def reset_squares
    @squares = {}
    self
  end

  def square(num)
    return @squares[num] if @squares[num]
    @squares[num] = 2**(num-1)
  end

  def total
    @squares.reduce(0) { |count, square| count + square[1] }
  end

end
