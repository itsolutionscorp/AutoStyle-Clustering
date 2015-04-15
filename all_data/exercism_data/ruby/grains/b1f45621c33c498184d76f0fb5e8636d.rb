class Grains
  def square(square)
    2**(square - 1)
  end

  def total
    (1..64).reduce{ |sum, n| sum + self.square(n) }
  end
end

class SpeedGrains
  @squares

  def initialize
    @squares = []
  end

  def square(square)
    if @squares[square]
      @squares[square]
    else
      @squares[square] = 2**(square - 1)
    end
  end

  def total
    (1..64).reduce{ |sum, n| sum + self.square(n) }
  end
end
