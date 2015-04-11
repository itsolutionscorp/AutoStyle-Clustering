class Raindrops 
  def initialize
    @str = ""
  end

  def Raindrops.convert num
    if divisible?(num, 3) 
      @str << "Pling"
    elsif divisible?(num, 5) 
      @str << "Plang"
    elsif divisible?(num, 7)
      @str << "Plong"
    else
      return num.to_s
    end
    return @str
  end

  def Raindrops.divisible?(num, i)
    num % i == 0
  end

end
