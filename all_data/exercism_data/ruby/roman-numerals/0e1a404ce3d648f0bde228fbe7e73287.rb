class Numeric
  @@arabicNumbers = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
  @@roman = ["M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"]

  def to_roman
    from(self)
  end

  private
  def from(arabic)

    for i in (0..@@arabicNumbers.size-1)
      if @@arabicNumbers[i] <= arabic then
        return @@roman[i] + from(arabic - @@arabicNumbers[i]);
      end
    end

    "";
  end
end
