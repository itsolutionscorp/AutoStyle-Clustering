class Grains

  def square(x)
    2 ** (x-1)
  end

  def total
    total = 0
    x = 1
    while x <= 64
      total += square(x)
      x += 1
    end
    total
  end
end
