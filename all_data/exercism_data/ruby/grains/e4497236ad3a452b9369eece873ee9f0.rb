class Grains

  SQUARES = 64

  def square(n)
    2 ** (n - 1)
  end

  def total
    (2 ** SQUARES) - 1
  end

end
