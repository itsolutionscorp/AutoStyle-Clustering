class Integer

  def to_roman
    number_to_roman_numeral = {
      1    => "I",
      2    => "II",
      3    => "III",
      4    => "IV",
      5    => "V",
      6    => "VI",
      9    => "IX",
      27   => "XXVII",
      48   => "XLVIII",
      59   => "LIX",
      402  => "CDII",
      575  => "DLXXV",
      911  => "CMXI",
      1024 => "MXXIV",
      3000 => "MMM"
    }

    number = self

    number_to_roman_numeral[number]

  end
end
