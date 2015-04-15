class Grains

  attr_reader :squares

  def initialize
    @squares = [1]
  end

  def square(i)
    while squares.length < i
      @squares << @squares.last * 2
    end
    return squares[i - 1]
  end

  def total
    square(64)
    squares[0..64].inject(:+)
  end
end
