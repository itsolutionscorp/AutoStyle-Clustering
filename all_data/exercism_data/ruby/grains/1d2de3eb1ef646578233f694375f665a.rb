class Grains
  SQUARE_COUNT = 64

  def square(i)
    2 ** (i-1)
  end

  def total
    square(SQUARE_COUNT + 1) - 1
  end
end
