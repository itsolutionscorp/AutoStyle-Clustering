class Grains
  SQUARES_ON_BOARD = 64

  def square(number)
    2**(number-1)
  end

  def total
    data = (1..SQUARES_ON_BOARD).map {|n| square(n)}
    data.reduce {|acc, n| acc += n }
  end

end
