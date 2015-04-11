module ToRoman
  TO_ROMAN = {
    1000 => "M",
    900  => "CM",
    500  => "D",
    400  => "CD",
    100  => "C",
    90   => "XC",
    50   => "L",
    40   => "XL",
    10   => "X",
    9    => "IX",
    5    => "V",
    4    => "IV",
    1    => "I"
  }

  def to_roman
    remainder = self.to_i

    TO_ROMAN.each_with_object("") do |(arabic_number, roman_number), out|
      count, remainder = remainder.divmod(arabic_number)
      out << roman_number * count
    end
  end
end


class Integer
  include ToRoman
end
