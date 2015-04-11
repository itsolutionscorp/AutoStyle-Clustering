class Grains
  def square(int)
    2**(int - 1) 
  end

  def total
    checker = 64
    total = 0
    while checker > 0
      total += square(checker)
      checker -= 1
    end
    total
  end

end
