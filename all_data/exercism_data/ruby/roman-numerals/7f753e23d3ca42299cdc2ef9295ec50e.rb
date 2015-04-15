class Fixnum

  def to_roman
    roman_string = []
    number = self
    thousands         = number / 1000
    if thousands > 0
      thousands.times { roman_string << "M" }
    end
    number            -= thousands * 1000
    nine_hundreds         = number / 900
    if nine_hundreds > 0
      nine_hundreds.times { roman_string << "CM" }
    end
    number            -= nine_hundreds * 900
    five_hundreds   = number / 500
    if five_hundreds > 0
      five_hundreds.times { roman_string << "D" }
    end
    number            -= five_hundreds * 500
    four_hundreds         = number / 400
    if four_hundreds > 0
      four_hundreds.times { roman_string << "CD" }
    end
    number            -= four_hundreds * 400
    hundreds          = number / 100
    if hundreds > 0
      hundreds.times { roman_string << "C" }
    end
    number            -= hundreds * 100
    nineties         = number / 90
    if nineties > 0
      nineties.times { roman_string << "XC" }
    end
    number            -= nineties * 90
    fifties                 = number / 50
    if fifties > 0
      fifties.times { roman_string << "L" }
    end
    number            -= fifties * 50
    forties         = number / 40
    if forties > 0
      forties.times { roman_string << "XL" }
    end
    number            -= forties * 40
    tens                   = number / 10
    if tens > 0
      tens.times { roman_string << "X" }
    end
    number            -= tens * 10
    nines                   = number / 9
    if nines > 0
      nines.times { roman_string << "IX" }
    end
    number            -= nines * 9
    fives                   = number / 5
    if fives > 0
      fives.times { roman_string << "V" }
    end
    number            -= fives * 5
    fours                  = number / 4
    if fours > 0
      fours.times { roman_string << "IV" }
    end
    number            -= fours * 4
    ones                   = number
    if ones > 0
      ones.times { roman_string << "I" }
    end
    roman_string.join
  end

end
