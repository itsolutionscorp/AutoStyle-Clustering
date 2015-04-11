module RomanNumerals

  ROMAN = { M: 1000, CM: 900, D: 500, CD: 400, C: 100, XC: 90,
            L: 50,   XL: 40,  X: 10,  IX: 9,   V: 5,   IV: 4,
            I: 1 }

  def to_roman
    num = self
    output = ''
    ROMAN.each do |symbol,v|
      while num/v > 0 do
        output << symbol.to_s
        num -= v
      end
    end
    output
  end

  Fixnum.send :include, RomanNumerals
end
