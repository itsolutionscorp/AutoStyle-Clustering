class Grains
  def square(x)
    x == 1 ? 1 : 2**(x - 1)
  end

  def total
    (2**64) - 1
  end
end
