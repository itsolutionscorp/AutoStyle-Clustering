def compute(a, b)
    diff = 0 
    count = 0
    b.split('').each do  |i|
      if i != a.split('')[count] 
        diff += 1
      end  
      count += 1  
    end 
    return diff  
  end