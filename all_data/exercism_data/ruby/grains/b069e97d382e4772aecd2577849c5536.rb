class Grains

  TOTAL_NUMBER_OF_SQUARES = 65
  def square(num)
    (2 ** (num - 1))
  end

  def total
    square(TOTAL_NUMBER_OF_SQUARES) - 1
  end

end
