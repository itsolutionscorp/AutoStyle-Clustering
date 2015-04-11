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
    count = self
    output = ""
    ROMAN_GLYPHS.to_a.reverse.each do |value, glyph|
      while count >= value
        output << glyph
        count -= value
      end
    end
    output
  end
end

class Integer
  include Roman
end
