class Integer
  ROMAN_NUMERALS = {1000 => "M", 900 => "CM", 500 => "D", 400 => "CD", 100 => "C", 90 => "XC", 50 => "L", 40 => "XL", 10 => "X", 9 => "IX", 5 => "V", 4 => "IV", 1 => "I"}

  def to_roman
    num = self
    output = ""

    ROMAN_NUMERALS.keys.each do |key|
      while num >= key do
        output += ROMAN_NUMERALS[key]
        num -= key
      end
    end

    output
  end
end

puts 1990.to_roman
