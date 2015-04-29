class Fixnum
  NUMERALS = {
    1    => 'I',
    5    => 'V',
    10   => 'X',
    50   => 'L',
    100  => 'C',
    500  => 'D',
    1000 => 'M'
  }

  def to_roman(input = self, place = 1000, output = '')
    return output if input == 0
    quot, rem = input.divmod(place)
    case quot
    when 0..3
      quot.times { output << NUMERALS[place] }
    when 4
      output << NUMERALS[place] << NUMERALS[place * 5]
    when 5..8
      output << NUMERALS[place * 5]
      (quot - 5).times { output << NUMERALS[place] }
    when 9
      output << NUMERALS[place] << NUMERALS[place * 10]
    end
    to_roman(rem, place / 10, output)
  end
end
