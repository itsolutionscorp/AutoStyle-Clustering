class Raindrops
  def Raindrops.convert(num)
    ncopy = num
    msg = ""
    return "1" if num == 1
    while num!=1
      if num%3 == 0
        msg << "Pling"
        while num%3 == 0
          num /= 3
        end
      end
      if num%5 == 0
        msg << "Plang"
        while num%5 == 0
          num /= 5          
        end
      end
      if num%7 == 0
        msg << "Plong"
        while num%7 == 0
          num/=7          
        end          
      end
      if num == ncopy
        return num.to_s
      else
        num/=num
      end
      return msg     
    end
  end
end
