class Grains
  def square(n)
    2**(n-1)
  end

  def total
    # a**n - 1 = (a - 1) * (1 + a**2 + ... + a**(n-1))
    2**64 - 1
  end
end
