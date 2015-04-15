class Grains
  def square(i)
    2 ** (i - 1)
  end

  def total
    (1..64).map { |i| square(i) }.reduce(:+)
  end
end
