class Fixnum
  def to_roman
    input = self
    result = []
    roman_numeral_key = {1 => "I", 4 => "IV", 5 => "V", 9 => "IX", 10 => "X", 40 => "XL", 50 => "L", 90 => "XC", 100 => "C", 400 => "CD", 500 => "D", 900 => "CM", 1000 => "M"}
    new_roman_numeral_key = Hash[roman_numeral_key.to_a.reverse]
    if input > 0
      new_roman_numeral_key.each do |number, roman_numeral|
        if input >= number
          result << roman_numeral
          input -= number
          redo
        end
      end
      return result.join.to_s
    end
  end
end
