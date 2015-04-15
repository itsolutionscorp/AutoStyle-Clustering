class Grains
  def square(index)
    1 << (index - 1)
  end

  def total
    (1 << 64) - 1
  end
end
