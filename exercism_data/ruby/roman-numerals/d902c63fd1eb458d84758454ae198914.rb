class Fixnum

  ROMAN_NUMERALS = {
    1000 => "M",
    900 => "CM",
    500 => "D",
    400 => "CD",
    100 => "C",
    90 => "XC",
    50 => "L",
    40 => "XL",
    10 => "X",
    9 => "IX",
    5 => "V",
    4 => "IV",
    1 => "I",
  }

  def to_roman
    return unless arab_roman_numeral_pair = ROMAN_NUMERALS.detect{ |arab_num, roman_num| arab_num <= self }
    arab_numeral, roman_numeral = arab_roman_numeral_pair
    div, mod = self.divmod(arab_numeral)
    "#{roman_numeral*div}" + "#{mod.to_roman}"
  end
end
