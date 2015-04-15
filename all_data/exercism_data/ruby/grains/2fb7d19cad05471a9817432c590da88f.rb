class Grains

  def square(a)
    2 ** (a - 1)
  end

  def total
    square(65) - 1
  end
end
