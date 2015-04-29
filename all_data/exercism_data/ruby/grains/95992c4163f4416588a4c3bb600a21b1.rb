class Grains

  TOTAL_SQUARES = 64

  def square(num)
    2 ** (num - 1)
  end

  def total
    (2 ** TOTAL_SQUARES) - 1
  end
end
