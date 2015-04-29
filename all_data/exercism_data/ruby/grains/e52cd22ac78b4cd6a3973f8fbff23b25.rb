class Grains
  def square(num)
    2**(num - 1)
  end

  def total(n = 64)
    2**n - 1
  end
end
