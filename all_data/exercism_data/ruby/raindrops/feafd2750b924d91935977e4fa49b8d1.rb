class Raindrops
  def self.convert(x)
    ret = ""

    if x % 3  == 0 
      ret =  "Pling"
    end

    if x % 5  == 0 
       ret +="Plang"
    end

    if x % 7  == 0 
       ret +="Plong"
    end

    ret != ""?  ret: x.to_s
  end
end
