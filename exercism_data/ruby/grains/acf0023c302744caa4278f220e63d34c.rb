class Grains

  attr_accessor :squares

  def initialize
    @squares = [0]
    fill_squares
  end

  def fill_squares
    @squares[1] = 1
    (2..64).each { |square|
      @squares[square] = (2 * @squares[square-1])
    }
  end

  def square(count)
    @squares[count]
  end

  def total
    @squares.reduce(:+)
  end

end
