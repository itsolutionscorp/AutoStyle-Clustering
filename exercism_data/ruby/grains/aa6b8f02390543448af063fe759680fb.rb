class Grains
  def square(index)
    2 ** (index - 1)
  end

  def total
    (1..64).map { |i| square(i) }.inject(:+).abs
  end
end
