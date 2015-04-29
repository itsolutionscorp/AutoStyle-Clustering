class Grains
  def square n
    2**(n-1)
  end

  def total
    (1..64).reduce { |total, n| total + square(n) }
  end
end
