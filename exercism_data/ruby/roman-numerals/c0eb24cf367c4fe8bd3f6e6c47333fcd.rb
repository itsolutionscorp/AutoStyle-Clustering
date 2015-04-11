module Roman
    ROMANS = [[1000, 'M'],
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

  def to_roman
    number = self
    result = ''

    ROMANS.each do |arabic, roman|
      quotient, remainder = number.divmod(arabic)
        result << roman * quotient
        number = remainder
    end
    result
  end
end

class Integer
  include Roman
end
