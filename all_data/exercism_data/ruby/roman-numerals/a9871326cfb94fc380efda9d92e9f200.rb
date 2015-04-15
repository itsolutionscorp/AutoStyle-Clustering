# Corey Haines Solution

class Integer
  def to_roman
    convert_to_roman self
  end

  private

  CONVERSIONS = [
    [1000, 'M'],
    [900,  'CM'],
    [500,  'D'],
    [400,  'CD'],
    [100,  'C'],
    [90,   'XC'],
    [50,   'L'],
    [40,   'XL'],
    [10,   'X'],
    [9,    'IX'],
    [5,    'V'],
    [4,    'IV'],
    [1,    'I'],
  ]

  def conversion_factors_for in_arabic
    CONVERSIONS.find { |arabic, _| arabic <= in_arabic }
  end

  def convert_to_roman in_arabic
    return '' if in_arabic.zero?
    arabic, roman = conversion_factors_for in_arabic
    roman + convert_to_roman(in_arabic - arabic)
  end
end
