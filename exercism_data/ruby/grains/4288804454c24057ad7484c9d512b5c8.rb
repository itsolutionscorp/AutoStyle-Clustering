# grains.rb
# Ruby grains exercise

class Grains
  SquaresOnBoard=64
  
  def square(n)
    2**(n-1)
  end
  
  def total(n=SquaresOnBoard)
    2**n - 1
  end
end
