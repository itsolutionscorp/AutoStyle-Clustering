class Fixnum

  ROMAN_TO_ARABIC = {
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
    number = self
    ROMAN_TO_ARABIC.each.reduce('') do |result, (roman, arabic)|
      count, number = number.divmod(arabic)
      result += roman.to_s * count
    end
  end
end
