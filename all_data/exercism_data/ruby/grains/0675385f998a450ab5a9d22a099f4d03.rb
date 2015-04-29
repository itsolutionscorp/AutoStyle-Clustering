class Grains
  # Number of grains of wheat in the nth square
  def square(n)
    2**(n - 1)
  end

  def total
    (1..64).inject(0) { |a, e| a + square(e) }
  end
end
