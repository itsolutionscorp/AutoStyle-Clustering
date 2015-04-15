class Raindrops
  def self.convert(x)
    message = []
    if x % 3 == 0
      message << "Pling"
    end
    
    if x % 5 == 0
      message << "Plang"
    end
    
    if x % 7 == 0
       message << "Plong"
    end
    
    if x % 3 != 0 && x % 5 != 0 && x % 7 != 0 
      x.to_s
    else
      message.join
    end
  end
  
end
