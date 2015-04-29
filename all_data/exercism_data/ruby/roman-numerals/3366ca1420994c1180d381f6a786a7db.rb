module RomanNumerals

  class << self

    def from_arabic(n)
      numerals.each_pair.with_object("") do |(value, chars), roman|
        while n >= value
          roman << chars
          n -= value
        end
      end
    end

    private

    def numerals
      {
        1000 => "M",
        900 => "CM",
        500 => "D",
        400 => "CD",
        100 => "C",
        90 => "XC",
        50 => "L",
        40 => "XL",
        10 => "X",
        9 => "IX",
        5 => "V",
        4 => "IV",
        1 => "I"
      }
    end

  end

end

class Fixnum

  def to_roman
    RomanNumerals.from_arabic(self)
  end

end
