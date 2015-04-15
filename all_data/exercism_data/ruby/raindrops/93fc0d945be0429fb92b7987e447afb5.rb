class Raindrops 

  def Raindrops.convert num
    str = ""
    
    if pling?(num) || plang?(num) || plong?(num)
      str << "Pling" if pling?(num) 
      str << "Plang" if plang?(num) 
      str << "Plong" if plong?(num) 
    else 
      return num.to_s
    end
    str
  end
  
  private

  def Raindrops.pling?(i)
    (i % 3) == 0
  end
    
  def Raindrops.plang?(i)
    (i % 5) == 0
  end
  
  def Raindrops.plong?(i)
    (i % 7) == 0
  end
end
