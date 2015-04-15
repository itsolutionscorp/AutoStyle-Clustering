class Grains

  def square(num)
    1 << (num - 1)
  end

  def total
    (1 << 64) - 1
  end
end
