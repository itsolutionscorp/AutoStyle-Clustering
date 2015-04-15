class Fixnum
  def to_roman
    thousands, remainder = to_thousands(self)
    hundreds, remainder  = to_hundreds(remainder)
    tens, remainder      = to_tens(remainder)
    ones, _              = to_ones(remainder)

    thousands + hundreds + tens + ones
  end

  private
    def to_thousands(x)
      roman = 'M' * (x / 1000)
      [roman, x.remainder(1000)]
    end

    def to_hundreds(x)
      h = x / 100

      roman = case
      when (h == 9)
        'CM'
      when (h >= 5)
        'D' + ('C' * (h - 5))
      when (h == 4)
        'CD'
      else
        'C' * h
      end

      [roman, x.remainder(100)]
    end

    def to_tens(x)
      t = x / 10

      roman = case
      when (t == 9)
        'XC'
      when (t >= 5)
        'L' + ('X' * (t - 5))
      when (t == 4)
        'XL'
      when (t > 0)
        'X' * t
      else
        ''
      end
      [roman, x.remainder(10)]
    end

    def to_ones(x)
      case
      when (x == 9)
        "IX"
      when (x >= 5)
        "V" + ("I" * (x - 5))
      when (x == 4)
        "IV"
      else
        "I" * x
      end
    end

end
