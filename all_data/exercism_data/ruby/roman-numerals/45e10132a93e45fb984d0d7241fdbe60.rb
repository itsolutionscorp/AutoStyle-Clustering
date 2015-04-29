module Roman

  ROMAN_VALUES = {
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
    1 => 'I'
  }

  ROMAN_KEYS = ROMAN_VALUES.keys

  def to_roman
    _to_roman(self, 1000, "")
  end

  private

  def _to_roman(number, cifer, roman_string)
    roman_string if number == 0
    if number < cifer
      _to_roman(number, _next_cifer(cifer), roman_string)
    else
      if number % cifer == 0
        roman_string + _liter_string(number, cifer)
      else
        _to_roman(number % cifer, _next_cifer(cifer), (roman_string + _liter_string(number, cifer)))
      end
    end
  end

  def _next_cifer(cifer)
    ROMAN_KEYS[ROMAN_KEYS.index(cifer)+1]
  end

  def _liter_string(number, cifer)
    ROMAN_VALUES[cifer]*(number/cifer)
  end

end

class Fixnum
  include Roman
end
