module RomanNumerals
  KEY = {
    1000 => 'M',
    900  => 'CM',
    500  => 'D',
    400  => 'CD',
    100  => 'C',
    90   => 'XC',
    50   => 'L',
    40   => 'XL',
    10   => 'X',
    9    => 'IX',
    5    => 'V',
    4    => 'IV',
    1    => 'I'
  }

  def to_roman(result = '')
    return result << '' if self <= 0
    return self if self >= 4000
    return result << KEY[self] if self == 1
    key = KEY.select { |k, _| k <= self }.to_a[0]
    result << KEY[key[0]] && (self - key[0]).to_roman(result)
  end
end
Fixnum.include(RomanNumerals)
