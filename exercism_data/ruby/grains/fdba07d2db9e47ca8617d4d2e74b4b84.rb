class Grains
  def square(num)
    2 ** (num - 1)
  end

  def total
    square(64) * 2 - 1
  end
end
