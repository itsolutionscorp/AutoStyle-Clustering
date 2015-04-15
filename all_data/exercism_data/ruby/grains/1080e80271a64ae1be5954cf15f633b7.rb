class Grains
  def square(n)
    return 1 if n == 1
    return 2 if n == 2
    return square(n-1) * 2
  end

  def total
    (0..64).inject { |sum, n| sum + square(n) }
  end
end
