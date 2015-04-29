class Grains
  def square(n)
    2 ** (n - 1)
  end

  def total(num_squares = 64)
    square(num_squares + 1) - 1
  end
end
