class Fixnum
  def to_roman
    number  = self
    numeral = ''

    digits = [
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

    digits.each do |limit, glyph|
      while number >= limit
        numeral << glyph
        number -= limit
      end
    end

    numeral
  end
end
