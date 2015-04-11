module Roman
  def to_roman
    roman = ''

    thousands = self / 1000
    value = self % 1000
    roman << 'M' * thousands if thousands > 0

    hundreds = value / 100
    value = value % 100
    roman << digit(hundreds, 'C', 'D', 'M')

    tens = value / 10
    roman << digit(tens, 'X', 'L', 'C')

    units = value % 10
    roman << digit(units, 'I', 'V', 'X')

    roman
  end

  private

  def digit(value, unit, five, ten)
    roman = ''
    if value == 9
      roman << "#{unit}#{ten}"
    elsif value > 4
      roman << five
      roman << unit * (value - 5)
    elsif value == 4
      roman << "#{unit}#{five}"
    else
      roman << unit * value
    end
    roman
  end
end

class Integer
  include Roman
end
