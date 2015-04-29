class Grains
  def square(n)
    2 ** n.pred
  end

  def total
    (1..64).reduce(0) { |sum, n| sum + square(n) }
  end
end
