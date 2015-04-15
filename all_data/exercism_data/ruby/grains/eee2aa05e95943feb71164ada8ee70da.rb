class Grains

  def square (casilla)
    i = 2
    sum = 1
    until i > casilla do
      sum = sum*2
      i +=1
    end
    return sum
  end

  def total
    return square(65)-1
  end

end
