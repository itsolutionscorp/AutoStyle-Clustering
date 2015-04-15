class Roman < Numeric
  STR_TO_INT = {
    "M"=> 1000, 
    "D"=> 500, 
    "C"=> 100, 
    "L"=> 50, 
    "X"=> 10, 
    "V"=> 5, 
    "I"=> 1, 
  }

  def romanize n
    STR_TO_INT.inject("") do |str,(letter,int)|
      #wont' handl 0
      str += letter*(n / int.to_i)
      n = n % int
      str
    end 
  end

  attr_reader :to_s
  def initialize(int)
    @to_s = romanize(int)
  end
  def ==(str)
    @to_s == str
  end
  def inspect
    "R:#{to_s}"
  end
end


class Numeric
  def to_roman
    ::Roman.new(self)
  end
end

class String 
  #include Roman::RomanMath

  def ==(int)
    if int.class <= Roman
      int == self
    else
      super 
    end
  end
end
