module Roman

  def to_roman
    ROMAN_VALUES.reduce ['', self] do |memo, roman_pair|
      str, remainder = *memo
      numeric_value, letter = *roman_pair
      repetitions, remainder = *remainder.divmod(numeric_value)
      str << letter * repetitions
      [str, remainder]
    end.first
  end

  private

  ROMAN_VALUES = {
    1000 =>  'M',
     900 => 'CM',
     500 =>  'D',
     400 => 'CD',
     100 =>  'C',
      90 => 'XC',
      50 =>  'L',
      40 => 'XL',
      10 =>  'X',
       9 => 'IX',
       5 =>  'V',
       4 => 'IV',
       1 =>  'I',
  }

  def digits
    to_s.chars.map &:to_i
  end
end

class Fixnum
  include Roman
end
