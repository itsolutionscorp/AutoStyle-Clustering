class Fixnum

  def to_roman
    Roman.new(self).convert_to_roman
  end

end

class Roman
  # I hate roman numerals
  
  attr_reader :number
  ROMANS = {1000=>"M", 900=>"CM", 500=>"D", 400=>"CD", 100=>"C", 90=>"XC", 50=>"L", 40=>"XL", 10=>"X", 9=>"IX", 5=>"V", 4=>"IV", 1=>"I"} 

  def initialize(number)
    @number = number
  end

  def convert_to_roman(n = number)
    roman = ''
    while n > 0
      n, str = build_roman_number(n)
      roman.concat(str)
    end
    roman
  end

  def build_roman_number(number)
    ROMANS.each do |key, value|
      if number >= key
        return [number - key, value]
      end
    end
  end
end
