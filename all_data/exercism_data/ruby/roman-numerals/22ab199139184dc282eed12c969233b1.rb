class Fixnum
  SYMBOLS = {
    1 => "I",
    5 => "V",
    10 => "X",
    50 => "L",
    100 => "C",
    500 => "D",
    1000 => "M"
  }

  def to_roman
    roman = ""
    remainder = self

    while remainder > 0 do
      magnitude = 10**(Math.log10(remainder).truncate)
      digit = (remainder / magnitude).truncate
      
      if digit == 9
        roman << SYMBOLS[magnitude] + SYMBOLS[10 * magnitude]
      elsif digit >= 5
        roman << SYMBOLS[5 * magnitude] + SYMBOLS[magnitude] * (digit - 5)
      elsif digit == 4
        roman << SYMBOLS[magnitude] + SYMBOLS[5 * magnitude]
      else
        roman << SYMBOLS[magnitude] * digit
      end
      remainder -= magnitude * digit
    end

    roman
  end
end
