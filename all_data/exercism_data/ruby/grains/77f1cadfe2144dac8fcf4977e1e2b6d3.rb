class Grains
  SQUARES_ON_BOARD = 64

  def square(n)
    return 1 if n == 1
    return square(n-1) * 2
  end

  def total
    (2 * square(SQUARES_ON_BOARD)) - 1
  end


end
