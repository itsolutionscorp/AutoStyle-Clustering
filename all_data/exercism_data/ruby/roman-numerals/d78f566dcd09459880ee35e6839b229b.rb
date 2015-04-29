TRANSLATIONS = {
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
    1 => "I",
  }

class Fixnum
  def to_roman
    current_value = self
    output = String.new
    TRANSLATIONS.each do |number, translation|
      while current_value >= number
        current_value -= number
        output << translation
      end
    end
    output
  end
end
