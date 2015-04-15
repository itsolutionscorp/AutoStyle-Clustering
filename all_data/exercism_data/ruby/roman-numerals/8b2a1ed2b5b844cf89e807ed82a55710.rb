class Fixnum
  def to_roman 
    number = self.to_s.reverse #reversed since it's easier than iterating backwards
    numeral = logic(number[0].to_i, "I", "V", "IX") #units - sending the numerals for 1, 5, 9 
    if number[1]
      tens = logic(number[1].to_i, "X", "L", "XC") #tens - numerals for 10, 50, 90
      numeral.insert(0, tens)
    end
    if number[2]
      hundreds = logic(number[2].to_i, "C", "D", "CM") #hundreds - numerals for 100, 500, 900
      numeral.insert(0, hundreds)
    end
    if number[3]
      thousands = th_logic(number[3].to_i)
      numeral.insert(0, thousands)
    end
    numeral
  end

  def logic (digit, one, five, nine) #same logic for 1-9, 10 - 90 & 100 - 900
    result = ""
    if digit > 4
      if digit == 9
        result << nine
        digit -= 9
      else
        result << five
        digit -= 5
      end
    end
    while digit > 0
      if digit < 4
        result << one
        digit -= 1
      else
        result << one + five
        digit -= 4
      end
    end
    result
  end

  def th_logic (digit)
    result = ""
    while digit > 0
      result << "M"
      digit -= 1
    end
    result
  end
end
