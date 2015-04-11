class Grains
  def square(n)
    return n if n < 3
    square(n-1) * 2
  end

  def total
    (0..64).inject { |sum, n| sum + square(n) }
  end
end
