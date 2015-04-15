class Grains
  def square(n)
    raise ArgumentError if n == 0
    raise ArgumentError if n > 64
    i = 1
    g = 1
    while i < n
      g *= 2
      i += 1
    end
    g
  end
  
  def total
    i = 1
    total = 0
    while i <= 64 do
      total += square(i)
      i += 1
    end
    total
  end
  
end
      
      
