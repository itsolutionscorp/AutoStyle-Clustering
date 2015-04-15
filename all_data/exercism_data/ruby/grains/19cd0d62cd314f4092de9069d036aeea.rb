class Grains
  def square(n)
    2 ** (n-1)
  end

  def total
    square(65) - 1
  end
end
