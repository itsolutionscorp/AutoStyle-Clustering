class Grains
  def square(n)
    n == 1 ? 1 : 2**(n-1)
  end

  def total
    square(65) - square(1)
  end
end
