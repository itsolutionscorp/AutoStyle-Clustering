class Integer
  ROMAN_ARABIC = {
    M:  1000,
    CM: 900,
    D:  500,
    CD: 400,
    C:  100,
    XC: 90,
    L:  50,
    XL: 40,
    X:  10,
    IX: 9,
    V:  5,
    IV: 4,
    I:  1
  }

  def to_roman
    remainder = self
    ROMAN_ARABIC.reduce("") do |roman_string, (roman_numeral, arabic_number)|
      times, remainder = remainder.divmod(arabic_number)
      roman_string + (roman_numeral.to_s * times)
    end
  end
end
