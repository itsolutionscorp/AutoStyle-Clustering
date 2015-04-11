class Grains
  
  def initialize 
    @squares = {}
  end

  def square no
    (2 ** no) / 2
  end
    
  
  def total
    (1..64).inject(0) do |sum, i| 
      sum += square(i) 
    end
  end
  
end
