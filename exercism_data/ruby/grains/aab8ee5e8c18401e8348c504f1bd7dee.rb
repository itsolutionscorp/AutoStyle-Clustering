class Grains
  def square(n)
    2**(n - 1)
  end

  def total
    (1..64).reduce(0) { |a, e| a + square(e) }
  end
end
