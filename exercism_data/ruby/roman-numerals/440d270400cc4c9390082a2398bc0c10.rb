class Fixnum
  def to_roman
    RomanNumeral.new(self).to_s
  end
  
  class RomanNumeral
    def initialize(value)
      @value = value
      @roman_numeral = ""
      calculate
    end
    
    def to_s
      @roman_numeral
    end
    
    private
    
    def calculate
      replace(1000, "M")
      
      replace(900, "CM")
      replace(500, "D")
      replace(400, "CD")
      replace(100, "C")
      
      replace(90, "XC")
      replace(50, "L")
      replace(40, "XL")
      replace(10, "X")
      
      replace(9, "IX")
      replace(5, "V")
      replace(4, "IV")
      replace(1, "I")
    end
    
    def replace(number, letter)
      while @value >= number
        @roman_numeral << letter
        @value -= number
      end
    end
  end
end
