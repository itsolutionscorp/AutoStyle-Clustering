class Integer
  ROMAN_MAP = [
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

  def to_roman result = ''
    return result if self.zero?

    ROMAN_MAP.each { |decimal, roman|
      quotient = self / decimal
      return (self - decimal * quotient).to_roman(result + roman * quotient) if quotient > 0
    }
  end
end
