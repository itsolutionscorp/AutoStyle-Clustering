class Grains
  def square(n)
    n == 1 || n == 2 ? n : 2**(n-1)
  end
  
  def total
    square(65) - 1
  end
end
