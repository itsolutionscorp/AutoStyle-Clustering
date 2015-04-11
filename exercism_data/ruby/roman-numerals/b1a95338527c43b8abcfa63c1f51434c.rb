NUMERALS = { 1000 => 'M', 500 => 'D', 900 => 'CM', 400 => 'CD',
             100 => 'C', 90 => 'XC', 50 => 'L', 40 => 'XL',
             10 => 'X',9 => 'IX', 5 => 'V', 4 => 'IV', 1 => 'I' }

class Fixnum
  def romanize(value, v = 10000)
    roman = ""
    inc = v / 10
    s = v - inc
    f = (5 * inc)
    o = (4 * inc)

    if ( value >= s )
      roman << NUMERALS[s]
      value -= s
    elsif (value >= f)
      roman << NUMERALS[f]
      value -= f
    elsif (value >= o)
      roman << NUMERALS[o]
      value -= o
    end

    while (value >= inc) do
      roman << NUMERALS[inc]
      value -= inc
    end

    if ( value > 0 )
      roman << romanize(value, inc)
    end
    roman
  end

  def to_roman
    romanize(self)
  end
end
