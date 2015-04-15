class Fixnum
  def to_roman
    string = ''
    number = self
    while number > 0
      if number >= 1000
        string += "M"*(number/1000)
        number -= number/1000*1000
      end
      if number >= 900
        string += "CM"
        number -= 900
      end
      if number >= 500
        string += "D"
        number -= 500
      end
      if number >= 400
        string += "CD"
        number -= 400
      end
      if number >= 100
        string += "C"*(number/100)
        number -= number/100*100
      end
      if number >= 90
        string += "XC"
        number -= 90
      end
      if number >= 50
        string += "L"
        number -= 50
      end
      if number >= 40
        string += "XL"
        number -= 40
      end
      if number >= 10
        string += "X"*(number/10)
        number -= number/10*10
      end
      if number == 9
        string += "IX"
        number -= 9
      end
      if number == 8
        string += "VIII"
        number -= 8
      end
      if number == 7
        string += "VII"
        number -= 7
      end
      if number == 6
        string += "VI"
        number -= 6
      end
      if number == 5
        string += "V"
        number -= 5
      end
      if number == 4
        string += "IV"
        number -= 4
      end
      if number == 3
        string += "III"
        number -= 3
      end
      if number == 2
        string += "II"
        number -= 2
      end
      if number == 1
        string += "I"
        number -= 1
      end
    end
    string
  end
end
