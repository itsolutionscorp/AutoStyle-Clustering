class Fixnum

  ROMAN_TO_ARABIC = {
    I: 1,
    IV: 4,
    V: 5,
    IX: 9,
    X: 10,
    XL: 40,
    L: 50,
    XC: 90,
    C: 100,
    CD: 400,
    D: 500,
    CM: 900,
    M: 1000
  }

  def to_roman
    remaining_number = self
    ROMAN_TO_ARABIC.keys.reverse_each.reduce("") do |result, roman|
      arabic = ROMAN_TO_ARABIC[roman]
      count = remaining_number.divmod(arabic)
      remaining_number = count[1]
      result += roman.to_s * count[0]
    end
  end
end
