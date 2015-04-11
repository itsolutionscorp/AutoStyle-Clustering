class Grains
  def square(i)
    2 ** (i-1)
  end

  def total
    totals = (1..64).collect { |i| square(i) }
    totals.inject(:+)
  end
end
