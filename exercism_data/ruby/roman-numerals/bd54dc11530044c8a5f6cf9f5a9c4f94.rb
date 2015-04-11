class Integer
  SYMBOLS = {
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
  }.sort.reverse

  def to_roman
    result = ""
    remainder = self

    SYMBOLS.each do |value, symbol|
      result << symbol * (remainder / value).truncate
      remainder = remainder % value
    end

    result
  end
end
