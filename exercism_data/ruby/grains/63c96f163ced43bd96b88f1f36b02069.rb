class Grains
  TOTAL_SQUARES = 64

  def square(number)
    2 ** (number - 1)
  end

  def total
    square(TOTAL_SQUARES + 1) - 1
  end
end
