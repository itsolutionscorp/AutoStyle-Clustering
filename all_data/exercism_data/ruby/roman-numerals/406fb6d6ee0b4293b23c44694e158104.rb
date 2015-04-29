module RomanNumerals
  ROMAN_MAPPING = {
    1000 => 'M',
    900 => 'CM',
    500 => 'D',
    400 => 'CD',
    100 => 'C',
    90 => 'XC',
    50 => 'L',
    40 => 'XL',
    10 => 'X',
    9 => 'IX',
    5 => 'V',
    4 => 'IV',
    1 => 'I',
  }

  def to_roman
    raise RangeError if self <= 0

    number = self
    roman = ""

    while number > 0
      ROMAN_MAPPING.keys.each do |k|
        if number >= k
          roman << ROMAN_MAPPING[k]
          number -= k
          break
        end
      end
    end

    roman
  end
end

Fixnum.include RomanNumerals
