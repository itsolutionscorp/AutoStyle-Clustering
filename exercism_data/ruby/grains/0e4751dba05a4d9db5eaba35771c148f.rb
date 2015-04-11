class Grains
  def square(n)
    2 ** (n - 1)
  end

  def total(squares_count = 64)
    square(squares_count + 1) - 1
  end
end
