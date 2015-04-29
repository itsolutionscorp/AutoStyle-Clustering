class Grains
  def square(index)
    2 ** (index - 1)
  end

  def total
    (2 ** 64) - 1
  end
end
