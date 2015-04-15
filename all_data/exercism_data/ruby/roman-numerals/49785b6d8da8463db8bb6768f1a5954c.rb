module Roman
  ROMAN_GLYPHS = {
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
  def to_roman
    count = self.to_i
    ROMAN_GLYPHS.sort.reverse.each_with_object("") do |(value, glyph), roman|
      while count >= value
        roman << glyph
        count -= value
      end
    end
  end
end

class Integer
  include Roman
end
