class Fixnum
  def to_roman
    number = self
    roman_representation = ""

    ARABIC_TO_ROMAN.each do |arabic, roman|
      while number >= arabic
        roman_representation += roman
        number -= arabic
      end
    end

    ROMAN_SUBSTITUTIONS.each do |this, that|
      roman_representation.gsub!(this, that)
    end

    roman_representation
  end


  ARABIC_TO_ROMAN = {
    1000 => "M",
    500  => "D",
    100  => "C",
    50   => "L",
    10   => "X",
    5    => "V",
    1    => "I"
  }

  ROMAN_SUBSTITUTIONS = {
    "IIII" => "IV",
    "VIV"  => "IX",
    "XXXX" => "XL",
    "LXL"  => "XC",
    "CCCC" => "CD",
    "DCD"  => "CM"
  }
end
