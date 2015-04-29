class Fixnum

  ROMAN_NUMBERS = { 1000 => "M", 900 => "CM", 500 => "D", 400 => "CD", 100 => "C", 90 => "XC", 50 => "L", 40 => "XL", 10 => "X", 9 => "IX", 5 => "V", 4 => "IV", 1 => "I" }

  def to_roman
    binding.pry
    result = ""
    number = self
    ROMAN_NUMBERS.each_key do |divisor|
      quotient, modulus = number.divmod(divisor)
      number = modulus
      result << ROMAN_NUMBERS[divisor] * quotient
    end
    result
  end
end
