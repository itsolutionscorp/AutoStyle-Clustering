module Roman
  def to_roman
    number = self
    roman_numerals.keys.inject('') do |str, key|
      times, (number, remainder) = number.divmod(key)
      str << roman_numerals[key] * times
    end
  end

  private

  def roman_numerals
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

class Fixnum
  include Roman
end
