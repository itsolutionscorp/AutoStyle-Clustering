class Integer
  ROMANS = [
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
    # With thanks to the late, great Jim Weirich
    fail 'No notation for 0 or negative number' if self <= 0

    number = self
    numeral = ''

    ROMANS.each do |value, glyph|
      while number >= value
        numeral << glyph
        number -= value
      end
    end
    numeral
  end
end
