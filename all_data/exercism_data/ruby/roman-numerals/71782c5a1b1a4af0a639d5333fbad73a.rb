class Fixnum
  def to_roman
    RomanNumeral.new(self).to_s
  end
end

class RomanNumeral
  attr_reader :value

  def initialize(int)
    @value = int
  end

  def to_s
    remainder = value
    string    = ""

    thousands      = remainder / 1000 # M
    remainder     -= thousands * 1000
    string        += "M" * thousands

    if remainder >= 900
      string    += "CM"
      remainder -= 900
    end

    five_hundreds  = remainder / 500  # D
    remainder     -= five_hundreds * 500
    string        += "D" * five_hundreds

    hundreds       = remainder / 100  # C
    remainder     -= hundreds * 100
    string        += (hundreds > 3 ? "CD" : "C" * hundreds)

    if remainder >= 90
      string    += "XC"
      remainder -= 90
    end

    fifties        = remainder / 50   # L
    remainder     -= fifties * 50
    string        += "L" * fifties

    tens           = remainder / 10   # X
    remainder     -= tens * 10
    string        += (tens > 3 ? "XL" : "X" * tens)

    if remainder == 9
      string    += "IX"
      remainder -= 9
    end

    fives          = remainder / 5    # V
    remainder     -= fives * 5
    string        += "V" * fives

    ones           = remainder        # I
    string        += (ones > 3 ? "IV" : "I" * ones)
  end
end
