module RomanNumeral

  NUMERALS = {
    m: 1000,
    cm: 900,
    d: 500,
    cd: 400,
    c: 100,
    xc: 90,
    l: 50,
    xl: 40,
    x: 10,
    ix: 9,
    v: 5,
    iv: 4,
    i: 1
  }

  def to_roman

    arabic = self
    roman = ''

    NUMERALS.each do |key, value|
      while arabic >= value
        arabic -= value
        roman += key.to_s.upcase
      end
    end

    roman
  end

end

class Integer
  include RomanNumeral
end
