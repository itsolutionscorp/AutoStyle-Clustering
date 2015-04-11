class Integer

  def to_roman
    number = self

    romans = [[1000, 'M'],
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
              [1, 'I']]

    result = ''

    romans.each do |pair|
      arabic, roman = pair
      quotient, remainder = number.divmod(arabic)
      if number >= arabic
        result << roman * quotient
        number = remainder
      end
    end
    result
  end

end
