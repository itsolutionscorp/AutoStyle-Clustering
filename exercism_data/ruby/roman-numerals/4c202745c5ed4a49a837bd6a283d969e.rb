class Fixnum

  ARABIC_TO_ROMAN_VALUE_MAP = [
      [1000, 'M'],
      [900, 'CM'],
      [500, 'D'],
      [400, 'CD'],
      [100, 'C'],
      [90, 'XC'],
      [50, 'L'],
      [40, 'XL'],
      [10, 'X'],
      [9, 'IX'],
      [5, 'V'],
      [4, 'IV'],
      [1, 'I']
    ]

  def to_roman
    temp = self
    result = ""
    ARABIC_TO_ROMAN_VALUE_MAP.each do |(arabic, roman)|
      while temp >= arabic
        temp -= arabic
        result += roman
      end
    end
    result
  end
end
