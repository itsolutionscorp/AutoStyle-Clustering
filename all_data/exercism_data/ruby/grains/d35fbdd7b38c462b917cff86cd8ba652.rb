class Grains
  def square(n)
    2 ** (n-1)
  end

  def total
    square(n) - 1
  end
end
