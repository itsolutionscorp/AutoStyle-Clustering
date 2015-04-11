class Fixnum
  def to_roman
    num = self
    numerals = {1000 => "M", 900 => "CM", 500 => "D", 400 => "CD", 100 => "C", 90 => "XC", 50 => "L", 40 => "XL", 10 => "X", 9 => "IX", 5 => "V", 4 => "IV", 1 => "I"}
    numerals.keys.inject("") do |memo, div|
      memo += (numerals[div] * (num/div))
      num = num % div
      memo
    end
  end
end
