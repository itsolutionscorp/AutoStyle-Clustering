class Fixnum
  ROMAN_MAP = { 1 => "I",
              4 => "IV",
              5 => "V",
              9 => "IX",
              10 => "X",
              40 => "XL",
              50 => "L",
              90 => "XC",
              100 => "C",
              400 => "CD",
              500 => "D",
              900 => "CM",
              1000 => "M" }

  # def to_roman
  #   number = self

  #   ROMAN_MAP.reduce("") do | roman_num, (letter, val)|
  #     while number >= val
  #       number -= val
  #       roman_num << letter
  #     end
  #     roman_num
  #   end
  # end

  # def to_roman
  #   ROMAN_MAP.key?(number) ? ROMAN_MAP.values_at(number) : conversion(number)
  # end

  def to_roman
    roman_numerals = Array.new(self) do |i|
      target = i + 1
      ROMAN_MAP.keys.sort { |a, b| b <=> a }.inject("") do |roman, div|
        times, target = target.divmod(div)
        roman << ROMAN_MAP[div] * times
      end
    end
    roman_numerals.last
  end
end
