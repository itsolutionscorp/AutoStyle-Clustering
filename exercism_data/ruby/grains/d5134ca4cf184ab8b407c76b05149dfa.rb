class Grains
  def initialize()
    @squares_ary = []
  
    for i in 1..64
      @squares_ary[i] = 1 << i - 1
    end
  end
  
  def square(location)
    @squares_ary[location]
  end  
  
  def total
    total_grains = 0
    
    for i in 1..64
      total_grains += @squares_ary[i]
    end  
      
    total_grains
  end
end
