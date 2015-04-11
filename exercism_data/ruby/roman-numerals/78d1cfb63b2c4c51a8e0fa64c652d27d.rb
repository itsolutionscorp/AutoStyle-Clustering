module RomanNumeral

  NUMERALS = {
    M: 1000,
    CM: 900,
    D: 500,
    CD: 400,
    C: 100,
    XC: 90,
    L: 50,
    XL: 40,
    X: 10,
    IX: 9,
    V: 5,
    IV: 4,
    I: 1
  }

  def to_roman

    arabic = self
    roman = ''

    NUMERALS.each do |key, value|
      while arabic >= value
        arabic -= value
        roman += key.to_s
      end
    end

    roman
  end

end

class Integer
  include RomanNumeral
end
