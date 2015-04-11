class Fixnum
  ROMAN_MAPPINGS = [
    1000, 900, 500, 400, 100,90, 50, 40, 10, 9, 5, 4, 1
    ].zip([
      "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
    ]).to_h

  def to_roman
    result = ""
    decimal = self

    ROMAN_MAPPINGS.each do |num, roman|
      while decimal >= num do
        decimal -= num
        result += roman
      end
    end

    result
  end
end
