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
    counts = ROMAN_LITERALS.keys.inject([self, []]) {|(number, counts), divisor|
      times, number = number.divmod(divisor)
      counts << times
      [number, counts]
    }.last

    counts.zip(ROMAN_LITERALS.values)
          .map {|times, literal| literal*times }
          .join

  end
end
