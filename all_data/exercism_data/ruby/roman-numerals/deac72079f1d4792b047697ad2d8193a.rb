class Fixnum
  ONES      = { 1 => "I", 2 => "II", 3 => "III", 4 => "IV", 5 => "V", 6 => "VI", 7 => "VII", 8 => "VIII", 9 => "IX" }
  TENS      = { 1 => "X", 2 => "XX", 3 => "XXX", 4 => "XL", 5 => "L", 6 => "LX", 7 => "LXX", 8 => "LXXX", 9 => "XC" }
  HUNDREDS  = { 1 => "C", 2 => "CC", 3 => "CCC", 4 => "CD", 5 => "D", 6 => "DC", 7 => "DCC", 8 => "DCCC", 9 => "CM" }
  THOUSANDS = { 1 => "M", 2 => "MM", 3 => "MMM" }
  def to_roman
    result = ""
    number = self.to_i
    if number/1000 > 0
      result << THOUSANDS[number/1000]
      number = number % 1000
    end
    if number/100 > 0
      result << HUNDREDS[number/100]
      number = number % 100
    end

    if number/10 > 0
      result << TENS[number/10]
      number = number % 10
    end

    if number > 0
      result << ONES[number]
    end
    result
  end
end
