class Grains
  def square(n)
    2**(n-1)
  end

  def total
    (1..64).reduce(0) { |sum, i| sum = sum + square(i) }
  end
end
