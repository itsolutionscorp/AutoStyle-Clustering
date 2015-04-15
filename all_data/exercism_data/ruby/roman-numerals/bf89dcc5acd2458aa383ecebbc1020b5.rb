module RomanNumerals
  NUMERALS = {
    1    => 'I',
    5    => 'V',
    10   => 'X',
    50   => 'L',
    100  => 'C',
    500  => 'D',
    1000 => 'M'
  }

  def base_to_roman(base)
    i = self % (base * 10) / base

    case i
    when 1..3 then NUMERALS[base] * i
    when 4    then NUMERALS[base] + NUMERALS[base * 5]
    when 5..8 then NUMERALS[base * 5] + NUMERALS[base] * (i - 5)
    when 9    then NUMERALS[base] + NUMERALS[base * 10]
    end
  end

  def to_roman
    [
      base_to_roman(1000),
      base_to_roman(100),
      base_to_roman(10),
      base_to_roman(1)
    ].join
  end
end

class Integer
  include RomanNumerals
end
