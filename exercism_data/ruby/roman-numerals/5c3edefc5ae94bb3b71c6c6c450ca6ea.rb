class Integer
  def to_roman
    numerals = {1000 => 'M', 900 => 'CM', 500 => 'D', 400 => 'CD', 100 => 'C', 90 => 'XC', 50 => 'L', 40 => 'XL', 10 => 'X', 9 => 'IX', 5 => 'V', 4 => 'IV', 1 => 'I'}
    out = ""
    i = self
    while i > 0
      numerals.each do |value, numeral|
        i, out = append_numeral(numeral, value, i, out)
      end
    end

    out
  end

  private
  def append_numeral(numeral, value, remaining, out)
    while remaining >= value
      out += numeral
      remaining -=value
    end
    return remaining, out
  end
end
