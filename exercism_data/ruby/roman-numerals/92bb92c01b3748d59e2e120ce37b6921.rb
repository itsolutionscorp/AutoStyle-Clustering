class Fixnum
  ROMANS = {
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
    1 => "I"
  }

  def to_roman
    ROMANS.keys.reduce(["", self]) do |(roman_num, arab_num), div|
      n, rest = arab_num.divmod(div)
      [roman_num << ROMANS[div] * n, rest]
    end[0]
  end
end
