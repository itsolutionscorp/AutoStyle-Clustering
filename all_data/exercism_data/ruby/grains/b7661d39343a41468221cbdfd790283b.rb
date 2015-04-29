class Grains
  
  def initalize
    @grains = 0
  end
  
  def square(no)
    
    if no == 1
      @grains = 1
    else
      @grains = 2 ** (no - 1)
    end
    
  end
  
  def total
    (1..64).map {|n| square(n)}.reduce(:+)
  end

end
