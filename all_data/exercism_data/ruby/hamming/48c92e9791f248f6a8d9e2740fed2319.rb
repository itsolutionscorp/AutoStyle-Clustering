class Hamming
  def Initialize
  end
  
  def compute(a,b)
    
    if a.size > b.size
      length = b.size
    else
      length = a.size
    end
    
    
    
    
    
    difference = 0
    0.upto(length-1) do |value|
     
      if a[value] == b[value]
        difference += 0
      else
        difference += 1
      end
    end
    return difference
        
  
  end
  
  
end

test = Hamming.new
