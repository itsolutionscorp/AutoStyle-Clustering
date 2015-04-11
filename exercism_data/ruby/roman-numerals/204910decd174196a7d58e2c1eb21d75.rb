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
      if number >= pair[0]
        result << pair[1] * number.div(pair[0])
        number = number % pair[0]
      end
    end
    result

  end

end
