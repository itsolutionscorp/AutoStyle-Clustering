class Fixnum
  def to_roman
    number = self
    roman_values = {M:1000, CM:900, D:500, CD:400, C:100, XC:90, L:50, XL:40, X:10, IX:9, V:5, IV:4, I:1}
    numerals = ""

    roman_values.each do |key, value|
      numeral_amount = number / value
      number -= value * numeral_amount

      numeral_amount.times do
        numerals += key.to_s
      end
    end

    numerals
  end
end
