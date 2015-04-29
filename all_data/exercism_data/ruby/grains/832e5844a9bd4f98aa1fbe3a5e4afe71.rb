class Grains

  def square(position)
    1 << (position-1)
  end

  def total
    square(65) - 1
  end

end
