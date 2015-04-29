class Grains
  TOTAL = 2**64-1

  def square n
    raise ArgumentError unless n > 0 && n < 65
    2**(n-1)
  end

  def total
    TOTAL
  end
end
