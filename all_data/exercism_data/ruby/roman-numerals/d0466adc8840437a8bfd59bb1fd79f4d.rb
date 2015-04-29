class Integer

  def to_roman
    number_to_roman_numeral = {
      1    => "I",
      4    => "IV",
      5    => "V",
      9    => "IX",
      10   => "X",
      40   => "XL",
      50   => "L",
      90   => "XC",
      100  => "C",
      400  => "CD",
      500  => "D",
      900  => "CM",
      1000 => "M"
    }

    copy = self

    string = ""
    until copy == 0
      match = number_to_roman_numeral.keys.select { |n| n <= copy }.max
      string << number_to_roman_numeral[match] * (copy / match)
      copy = copy % match
    end
    string

  end
end
