class Raindrops
  def self.convert(int)
    output=''
    int=int.to_i
    if !(int%3==0||int%5==0||int%7==0)
      return int.to_s
    end
    if int%3==0
      output+='Pling'
    end
    if int%5==0
      output+='Plang'
    end
    if int%7==0
      output+='Plong'
    end
    output
  end
end
