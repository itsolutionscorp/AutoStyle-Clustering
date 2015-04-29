class Grains
  
  def initalize
    @grains = 0
  end
  
  def square(no)
      @grains = 2 ** (no - 1)
    
  end
  
  def total
    (1..64).map {|n| square(n)}.reduce(:+)
  end

end
