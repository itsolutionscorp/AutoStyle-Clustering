class Grains
  def square(exp)
    return 2 ** (exp-1)
  end
  
  def total(exp = 64)
    return 2 ** exp - 1
  end
end
