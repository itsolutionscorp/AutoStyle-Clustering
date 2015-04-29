class Grains

  def square(value)
    1 << (value) >> 1
  end

  def total
    square(65) - 1
  end

end
