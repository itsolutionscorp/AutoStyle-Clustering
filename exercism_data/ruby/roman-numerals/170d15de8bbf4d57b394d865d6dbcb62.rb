NUMERALS = { 1000 => 'M', 500 => 'D', 100 => 'C', 50 => 'L', 10 => 'X', 5 => 'V', 1 => 'I' }

class Fixnum
  def romanize(value, v = 10000)
    roman = ""
    inc = v / 10
    s = v - inc
    f = (5 * inc)
    o = (4 * inc)
    mm = v - s

    if ( value >= s )
      roman += NUMERALS[mm] + NUMERALS[v]
      value -= s
    elsif (value >= f)
      roman += NUMERALS[f]
      value -= f
    elsif (value >= o)
      roman += NUMERALS[inc] + NUMERALS[f]
      value -= o
    end

    while (value >= inc) do
      roman += NUMERALS[inc]
      value -= inc
    end

    if ( value > 0 )
      roman += romanize(value, inc)
    end
    roman
  end

  def to_roman
    romanize(self)
  end
end
