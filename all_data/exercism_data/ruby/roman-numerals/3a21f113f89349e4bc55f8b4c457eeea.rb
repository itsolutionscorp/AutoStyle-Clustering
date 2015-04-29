class RomanNumerals
  ROMANS= {
    1 => "I",
    4 => "IV",
    5 => "V",
    9 => "IX",
    10 => "X",
    40 => "XL",
    50 => "L",
    90 => "XC",
    100 => "C",
    400 => "CD",
    500 => "D",
    900 => "CM",
    1000 => "M"
  }
  def self.process(number)
    ROMANS.keys.reverse.inject("") do |roman, div|
      times, number = number.divmod(div)
      roman << ROMANS[div] * times
    end
  end
end

class Integer
  def to_roman
    RomanNumerals.process(self)
  end
end
