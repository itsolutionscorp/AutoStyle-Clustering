class Grains
  def square(n)
    2**(n - 1)
  end

  # a = initial value = 1
  # r = ratio         = 2
  # n = num items     = 64
  # a( (1 - r**n) / (1 - r) )
  # in this case this simplifies to 2**64 - 1
  def total
    2**64 - 1
  end
end
