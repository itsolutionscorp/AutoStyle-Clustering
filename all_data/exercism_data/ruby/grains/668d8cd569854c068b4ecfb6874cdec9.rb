class Grains

  def total
    (1..64).reduce { |acc, n| acc + square(n) }
  end

  def square(n)
    1 << n-1
  end
end
