class Raindrops
  def convert(num)
    rsc=""
    arr= conc_p(num)
    arr.each do |a|
      rs=get_raindrop_speak(a)
      rsc += rs.to_s 
    end
    if rsc.length == 0
      return num.to_s
    else
      return rsc.to_s
    end
  end

  def conc_p(num)
    piv = 2
    arr = Array.new
    while num != 1
      if num % piv == 0
        if not arr.include?(piv)
          arr.push(piv)          
        end        
        num = num / piv
      else      
        piv+=1
      end
    end
    return arr
  end

  def get_raindrop_speak(num)
    case num
    when 3
      "Pling"
    when 5
      "Plang"
    when 7
      "Plong"
    else
      ""
    end
  end
  
end
