class Fixnum
  def to_roman
  number = self
  roman = ""

  if number > 899
    number / 1000 < 1 ? thousands = 1 : thousands = number / 1000
    thousands.times{
      if number < 1000
        roman << "CM"
        number -= 900
      elsif number > 901 && (number < 1100 || number > 1200)
        roman << "M"
        number -= 1000
      elsif number > 1001 && number < 1200
        number -= 1100
        roman << "MC"
      end
    }
  end

  if number > 399
    number / 500 < 1 ? fiveHundreds = 1 : fiveHundreds = number / 500
    fiveHundreds.times{
      if number < 500
        roman << "CD"
        number -= 400
      elsif number > 401 && (number < 600 || number > 650)
        roman << "D"
        number -= 500
      elsif number > 501 && number < 650
        roman << "DC"
        number -= 600
      end
    }
  end

  if number > 89
    number / 100 < 1 ? hundreds = 1 : hundreds = number / 100
    hundreds.times{
      if number < 100
        roman << "XC"
        number -= 90
      elsif number > 89 && number < 110
        roman << "C"
        number -= 100
      elsif number > 120 && (number < 150 || number > 160)
        roman << "C"
        number -= 100
      elsif number > 109 && number < 120
        roman << "CX"
        number -= 110
      end
    }
  end

  if number > 39
    number / 50 < 1 ? fifties = 1 : fifties = number / 50
    fifties.times{
      if number > 49 && number < 60
        roman << "L"
        number -= 50
      elsif number < 50
        roman << "XL"
        number -= 40
      elsif number > 59
        roman << "LX"
        number -= 60
      end
    }
  end

  if (number % 10 == 9 || number % 10 == 0 || number % 10 == 1) && (number > 8)
    number / 10 < 1 ? tens = 1 : tens = number / 10
    tens.times{
    if number % 10 == 9
        number -= 9
        roman << "IX"
    elsif number % 10 == 1
      roman << "XI"
      number -= 11
    elsif number % 10 == 0
      roman << "X"
      number -= 10
    end
    }
  elsif number > 11
    tens = number / 10
    tens.times{
      roman << "X"
      number -= 10
    }
  end

  if (number % 5 == 4 || number % 5 == 0 || number % 5 == 1) && number > 3
    number / 5 < 1 ? fives = 1 : fives = number / 5
      fives.times{
        if number % 5 == 4
          number -= 4
          roman << "IV"
        elsif number % 5 == 1
          roman << "VI"
          number -= 6
        elsif number % 5 == 0
          roman << "V"
          number -= 5
        end
      }
  elsif number > 6
    fives = number / 5
      fives.times{
          roman << "V"
          number -= 5
      }
  end

  number.times{|x| roman << "I"}
  roman
  end
end
