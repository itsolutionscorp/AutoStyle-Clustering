class Grains

  TOTAL = 64

  def square(num)
    2 ** (num - 1)
  end

  def total
    (1 - 2 ** TOTAL) / (1 - 2)
  end

end
