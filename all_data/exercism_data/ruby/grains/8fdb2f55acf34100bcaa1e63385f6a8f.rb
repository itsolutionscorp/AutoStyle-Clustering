class Grains
  # Number of grains of wheat in the nth square
  def square(n)
    2**(n - 1)
  end

  def total
    squares = 64
    2**squares - 1
  end
end
