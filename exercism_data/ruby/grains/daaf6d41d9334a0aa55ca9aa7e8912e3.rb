class Grains
  def square number
    2 ** (number - 1)
  end

  def total
    square(64) * 2 - 1
  end
end
