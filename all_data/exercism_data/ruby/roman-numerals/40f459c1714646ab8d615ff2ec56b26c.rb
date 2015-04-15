class Integer
  ROMAN_DIGITS = {
    0 => "",
    1 => 'I',
    2 => 'II',
    3 => 'III',
    4 => 'IV',
    5 => 'V',
    6 => 'VI',
    7 => 'VII',
    8 => 'VIII',
    9 => 'IX'
  }

  ROMAN_TENS = {
    0 => "",
    10 => 'X',
    20 => 'XX',
    30 => 'XXX',
    40 => 'XL',
    50 => 'L',
    60 => 'LX',
    70 => 'LXX',
    80 => 'LXXX',
    90 => 'XC'
  }

  ROMAN_HUNDREDS = {
    0 => "",
    100 => 'C',
    200 => 'CC',
    300 => 'CCC',
    400 => 'CD',
    500 => 'D',
    600 => 'DC',
    700 => 'DCC',
    800 => 'DCCC',
    900 => 'CM'
  }

  ROMAN_THOUSANDS = {
    0 => "",
    1000 => 'M',
    2000 => 'MM',
    3000 => 'MMM'
  }

  def to_roman
    romanized = ""
    num = self
    if num > 1000
      romanized += ROMAN_THOUSANDS[(num / 1000) * 1000]
      num -= (num / 1000) * 1000
    end

    if num > 100
      romanized += ROMAN_HUNDREDS[(num / 100) * 100]
      num -= (num / 100) * 100
    end

    if num > 10
      romanized += ROMAN_TENS[(num / 10) * 10]
      num -= (num / 10) * 10
    end

    romanized += ROMAN_DIGITS[num]
  end
end
