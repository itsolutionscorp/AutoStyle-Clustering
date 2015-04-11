class Numeric
  ARABIC = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
  ROMAN = ["M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"]

  def to_roman
    from(self)
  end

  private
  def from(arabic)

    (0...Numeric::ARABIC.size).each do |i|
      if Numeric::ARABIC[i] <= arabic then
        return Numeric::ROMAN[i] + from(arabic - Numeric::ARABIC[i]);
      end
    end

    "";
  end
end
