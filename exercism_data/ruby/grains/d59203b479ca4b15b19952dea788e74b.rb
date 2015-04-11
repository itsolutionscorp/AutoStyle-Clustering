class Grains

  TOTAL_SQUARES = 64

  def square(i)
    2 ** (i-1)
  end

  def total
    totals = (1..TOTAL_SQUARES).collect { |i| square(i) }
    totals.inject(:+)
  end
end
