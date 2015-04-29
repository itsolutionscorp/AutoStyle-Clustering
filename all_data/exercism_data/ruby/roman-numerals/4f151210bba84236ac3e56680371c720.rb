module Roman
  def to_roman
    roman_numerals(ord)
  end

  private

  def mappings
    {
      1000 =>  'M',
      900  => 'CM',
      500  =>  'D',
      400  => 'CD',
      100  =>  'C',
      90   => 'XC',
      50   =>  'L',
      40   => 'XL',
      10   =>  'X',
      9    => 'IX',
      5    =>  'V',
      4    => 'IV',
      1    =>  'I'
    }
  end

  def numbers_in_order
    mappings.keys
  end

  def roman_numerals(number, numerals = numbers_in_order, string = '')
    return string unless number > 0

    next_numeral = numerals.first
    if number >= next_numeral
      roman_numerals(number - next_numeral, numerals, string += mappings[next_numeral])
    else
      roman_numerals(number, numerals[1..-1], string)
    end
  end
end

class Fixnum
  include Roman
end
