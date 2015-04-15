class Raindrops
  def self.div3(int)
    int%3==0
  end

  def self.div5(int)
    int%5==0
  end

  def self.div7(int)
    int%7==0
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
