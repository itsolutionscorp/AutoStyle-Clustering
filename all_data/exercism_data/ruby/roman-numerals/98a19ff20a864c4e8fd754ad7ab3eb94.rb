  class Fixnum
    def to_roman
      value = self
      roman = ""
      while value > 0
        if value >= 1000
          roman += "M"
          value -= 1000
        elsif value > 899
          roman += "CM"
          value -= 900
        elsif value > 499
          roman += "D"
          value -= 500
        elsif value > 399
          roman += "CD"
          value -= 400
        elsif value > 99
          roman += "C"
          value -= 100
        elsif value > 89
          roman += "XC"
          value -= 90
        elsif value > 49
          roman += "L"
          value -= 50
        elsif value > 39
          roman += "XL"
          value -= 40
        elsif value > 9
          roman += "X"
          value -= 10
        elsif value > 8
          roman += "IX"
          value -= 9
        elsif value > 4
          roman += "V"
          value -= 5
        elsif value > 3
          roman += "IV"
          value -= 4
        elsif value > 0
          roman += "I"
          value -= 1
        end
      end
      roman
    end
  end
