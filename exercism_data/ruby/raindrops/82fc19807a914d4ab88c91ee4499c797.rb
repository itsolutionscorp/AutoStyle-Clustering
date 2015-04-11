class Raindrops 

  def Raindrops.convert num
    str = ""
    if (num % 3 == 0) 
      str << "Pling"
    end
    if (num % 5 == 0)
      str << "Plang"
    end
    if (num % 7 == 0)
      str << "Plong"
    end
    unless num % 3 == 0 || num % 5 == 0 || num % 7 == 0
      return num.to_s
    end
    return str
  end
end
