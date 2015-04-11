class Integer
  
  def to_roman()
    
    arabic_number = self
    roman_equivalent = []

    if arabic_number > 3000
      raise "Sorry, .to_roman is not built to work with numbers >3000"
    end

    case (arabic_number % 10)
      when 1 ; roman_equivalent.unshift "I"
      when 2 ; roman_equivalent.unshift "II"
      when 3 ; roman_equivalent.unshift "III"
      when 4 ; roman_equivalent.unshift "IV"
      when 5 ; roman_equivalent.unshift "V"
      when 6 ; roman_equivalent.unshift "VI"
      when 7 ; roman_equivalent.unshift "VII"
      when 8 ; roman_equivalent.unshift "VIII"
      when 9 ; roman_equivalent.unshift "IX"
    end
    arabic_number -= (arabic_number % 10)

    case (arabic_number % 100)
      when 10 ; roman_equivalent.unshift "X"
      when 20 ; roman_equivalent.unshift "XX"
      when 30 ; roman_equivalent.unshift "XXX"
      when 40 ; roman_equivalent.unshift "XL"
      when 50 ; roman_equivalent.unshift "L"
      when 60 ; roman_equivalent.unshift "LX"
      when 70 ; roman_equivalent.unshift "LXX"
      when 80 ; roman_equivalent.unshift "LXXX"
      when 90 ; roman_equivalent.unshift "XC"
    end
    arabic_number -= (arabic_number % 100)

    case (arabic_number % 1000)
      when 100 ; roman_equivalent.unshift "C"
      when 200 ; roman_equivalent.unshift "CC"
      when 300 ; roman_equivalent.unshift "CCC"
      when 400 ; roman_equivalent.unshift "CD"
      when 500 ; roman_equivalent.unshift "D"
      when 600 ; roman_equivalent.unshift "DC"
      when 700 ; roman_equivalent.unshift "DCC"
      when 800 ; roman_equivalent.unshift "DCCC"
      when 900 ; roman_equivalent.unshift "CM"
    end
    arabic_number -= (arabic_number % 1000)


    case (arabic_number % 10000)
      when 1000 ; roman_equivalent.unshift "M"
      when 2000 ; roman_equivalent.unshift "MM"
      when 3000 ; roman_equivalent.unshift "MMM"
    end
  
    return roman_equivalent.join

  end

end
