class Grains
  def square(n)
    1 << (n - 1)
  end

  def total
    square(65) - 1
  end
end
