class Grains
  def initialize
    @squares = (0..63).map { |e| 2**e  }
  end

  def square(num)
    if (num < 1) || (num > 64)
      raise ArgumentError "Squares are 1 to 64 nothing more, nothing less"
    end
    @squares[num - 1]
  end

  def total
    @squares.reduce(:+)
  end

end
