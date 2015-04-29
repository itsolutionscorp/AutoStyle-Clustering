class Grains

  def square(posi)
    return (2**posi)/2
  end 

  def total()
    acum = 0
    i = 1
    while i <= 64
        acum = acum + square(i)        
        i= i + 1        
    end    
    return acum
  end   

end
