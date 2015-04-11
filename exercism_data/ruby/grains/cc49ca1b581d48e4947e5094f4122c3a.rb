class Grains
  def square(n)
    2 ** (n - 1)
  end

  def total
    2 ** 64 - 1 # (1..64).map { |n| square(n) }.reduce(:+)
  end
end
