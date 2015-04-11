class Grains

  def square(n)
    return 1 if n == 1
    square(n - 1) * 2
  end
  
  def total
    2 ** 64 - 1
  end
  
end
