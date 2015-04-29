class Fixnum
  def to_roman
    number = self
    roman_values = {M:1000, CM:900, D:500, CD:400, C:100, XC:90, L:50, XL:40, X:10, IX:9, V:5, IV:4, I:1}

    roman_values.keys.reduce("") do |numerals, key|
      numeral_amount = number / roman_values[key]
      number -= roman_values[key] * numeral_amount
      numerals + key.to_s * numeral_amount
    end
  end
end
