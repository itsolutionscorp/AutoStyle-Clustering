class Grains
  def square(i)
    x = 1
    (i-1).times { x *= 2 }
    x
  end

  def total
    totals = (1..64).collect { |i| square(i) }
    totals.inject(:+)
  end
end
