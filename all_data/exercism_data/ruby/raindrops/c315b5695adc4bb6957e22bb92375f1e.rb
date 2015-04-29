class Raindrops 
  def initialize
    @str = ""
  end

  def Raindrops.convert num
    if (num % 3 == 0) 
      @str << "Pling"
    elsif (num % 5 == 0)
      @str << "Plang"
    elsif (num % 7 == 0)
      @str << "Plong"
    else
      return num.to_s
    end
    return @str
  end
end
