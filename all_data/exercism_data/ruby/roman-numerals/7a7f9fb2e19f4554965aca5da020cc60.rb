class Integer
  ROMAN_DIVISORS = {
    1 => "I",
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
    1000 => "M"
  }.freeze

  def to_roman
    number = self
    symbols = ROMAN_DIVISORS.to_a.reverse.map do |divisor, symbol|
      count, number = number.divmod(divisor)
      symbol * count unless count.zero?
    end.join
  end
end
