class Grains
  TOTAL_NUMBER_OF_SQUARES = 64

  def square(i)
    (2 ** (i - 1))
  end

  def total
    (2 ** TOTAL_NUMBER_OF_SQUARES) - 1
  end
end
