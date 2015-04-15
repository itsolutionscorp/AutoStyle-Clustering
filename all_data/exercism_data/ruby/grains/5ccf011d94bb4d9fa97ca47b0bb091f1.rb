class Grains
  SQUARES_ON_BOARD = 64

  def square(num)
    2 ** (num-1)
  end

  def total
    # (2**4)-1 == 2**3 + 2**2 + 2**1 + 2**0
    (2 ** SQUARES_ON_BOARD) - 1
  end

end
