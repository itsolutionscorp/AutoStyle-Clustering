class Raindrops
  def self.div3(int)
    if int%3==0
      return true
    else
      return false
    end
  end

  def self.div5(int)
    if int%5==0
      return true
    else
      return false
    end
  end

  def self.div7(int)
    if int%7==0
      return true
    else
      return false
    end
  end

  def self.convert(int)
    output=''
    int=int.to_i
    if !(self.div3(int)||self.div5(int)||self.div7(int))
      return int.to_s
    end
    if self.div3(int)
      output+='Pling'
    end
    if self.div5(int)
      output+='Plang'
    end
    if self.div7(int)
      output+='Plong'
    end
    return output
  end
end
