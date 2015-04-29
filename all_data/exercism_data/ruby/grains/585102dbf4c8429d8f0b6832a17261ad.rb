class Grains
  def square(num)
    2 ** (num - 1)
  end

  def total
    (1..64).to_a.inject { |sum, n| sum + square(n)}
  end
end
