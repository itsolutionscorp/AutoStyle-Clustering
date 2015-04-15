class Fixnum

  def to_roman
    NumberConverter.to_roman(self)
  end

end

module NumberConverter
  
  extend self

  ARABIC_TO_ROMAN_MAPPING = {
    0 => "",
    1 => "I",
    2 => "II",
    3 => "III",
    4 => "IV",
    5 => "V",
    6 => "VI",
    7 => "VII",
    8 => "VIII",
    9 => "IX",
    10 => "X",
    20 => "XX",
    30 => "XXX",
    40 => "XL",
    50 => "L",
    60 => "LX",
    70 => "LXX",
    80 => "LXXX",
    90 => "XC",
    100 => "C",
    200 => "CC",
    300 => "CCC",
    400 => "CD",
    500 => "D",
    600 => "DC",
    700 => "DCC",
    800 => "DCC",
    900 => "CM",
    1000 => "M",
    2000 => "MM",
    3000 => "MMM"
  }

  def to_roman(arabic_numeral)
    ARABIC_TO_ROMAN_MAPPING.fetch(arabic_numeral) do
      separated_nums = separate_numbers(arabic_numeral)
      count = separated_nums.count
      roman_numeral = ""
      while count > 0
        current_decimal_part = to_decimal_part(separated_nums.shift, count)
        roman_numeral << to_roman(current_decimal_part)
        count -= 1 
      end
      roman_numeral
    end
  end

  private

    def separate_numbers(numeral)
      numeral.to_s.split("")
    end

    def to_decimal_part(digit, numbers_count)
      (digit.to_s + "0" * (numbers_count - 1)).to_i
    end

end
