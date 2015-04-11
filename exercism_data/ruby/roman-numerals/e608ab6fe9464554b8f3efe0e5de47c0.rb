class Roman
  def self.numerals(number)
    return '' if number < 1
    part, numeral = mapping.detect { |part, _| part <= number }
    numeral + numerals(number - part)
  end

  def self.mapping
    [
      [1000, 'M'],
      [ 900, 'CM'],
      [ 500, 'D'],
      [ 400, 'CD'],
      [ 100, 'C'],
      [  90, 'XC'],
      [  50, 'L'],
      [  40, 'XL'],
      [  10, 'X'],
      [   9, 'IX'],
      [   5, 'V'],
      [   4, 'IV'],
      [   1, 'I']
    ]
  end
end

class Fixnum
  def to_roman
    Roman.numerals(self)
  end
end
