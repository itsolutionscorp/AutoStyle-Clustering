class RomanConverter
  def initialize(arabic_number)
    @arabic_number = arabic_number
  end

  def roman_number
    value = arabic_number
    result = ""
    while value > 0
      arabic, roman = largest_mapping(value)
      value -= arabic
      result += roman
    end
    result
  end

  private

  def largest_mapping(value)
    mappings.find do |convertable_arabic_part, _|
      value >= convertable_arabic_part
    end
  end

  def mappings
    [
      [1000, "M"],
      [900, "CM"],
      [500, "D"],
      [400, "CD"],
      [100, "C"],
      [90, "XC"],
      [50, "L"],
      [40, "XL"],
      [10, "X"],
      [9, "IX"],
      [5, "V"],
      [4, "IV"],
      [1, "I"],
    ]

  end
  attr_reader :arabic_number
end

class Fixnum
  def to_roman
    RomanConverter.new(self).roman_number
  end
end
