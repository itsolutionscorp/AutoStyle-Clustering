# Monkey patching a core class shouldn't be necessary for this

class Fixnum
  ROMAN_LITERALS = {
    1000 => 'M',
     900 => 'CM',
     500 => 'D',
     400 => 'CD',
     100 => 'C',
      90 => 'XC',
      50 => 'L',
      40 => 'XL',
      10 => 'X',
       9 => 'IX',
       5 => 'V',
       4 => 'IV',
       1 => 'I'
  }

  def to_roman
    parts = ROMAN_LITERALS.keys.inject([self, []]) {|(number, parts), divisor|
      part, number = number.divmod(divisor)
      parts << part
      [number, parts]
    }.last

    roman_number = parts.zip(ROMAN_LITERALS.values)
                        .map {|times, literal| literal*times }
                        .join

    roman_number
  end
end
