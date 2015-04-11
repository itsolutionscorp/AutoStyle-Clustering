class Grains
  def square(n)
    2**(n-1)
  end

  def total
    n = 64
    sum = 0
    while n > 0 do
      sum += 2**(n-1)
      n -= 1
    end
    sum
  end
end
