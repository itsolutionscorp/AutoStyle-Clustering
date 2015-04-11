class Fixnum
  def to_roman
    roman = ''
    thousands = self / 1000
    rem_of_thou = self % 1000
    hund_table = [nil, 'C', 'CC', 'CCC',
                  'CD', 'D', 'DC', 'DCC', 'DCCC', 'CM']
    tens_table = [nil, 'X', 'XX', 'XXX',
                  'XL', 'L', 'LX', 'LXX', 'LXXX', 'XC', 'X']
    ones_table = [nil, 'I', 'II', 'III', 'IV', 'V', 'VI',
                  'VII', 'VIII', 'IX']

    roman += 'M' * thousands if thousands > 0

    hundreds = rem_of_thou / 100
    rem_of_hun = rem_of_thou % 100

    roman += hund_table[hundreds] if hundreds > 0

    tens = rem_of_hun / 10
    ones = rem_of_hun % 10

    roman += tens_table[tens] if tens > 0

    roman += ones_table[ones] if ones > 0
    return roman
  end
end
