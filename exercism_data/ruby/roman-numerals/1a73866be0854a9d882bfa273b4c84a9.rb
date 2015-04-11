#Sweet! We can extend Ruby's built-in Integer class
class Integer
  def to_roman
    result = ""

    digits = '%04i' % self
    digits = digits.chars.map(&:to_i)
    result += convert(digits[0], "M", "", "")
    result += convert(digits[1], "C", "D", "M")
    result += convert(digits[2], "X", "L", "C")
    result += convert(digits[3], "I", "V", "X")
  end

  def convert(digit, smallRoman, midRoman, largeRoman)
    if digit.nil?; return "" end
    result = ""
    if (digit >= 5)
      result += midRoman
    end
    result += smallRoman * (digit % 5)
    result.gsub!(/#{"#{smallRoman}"*4}/, "#{smallRoman}#{midRoman}")
    result.gsub!(/#{"#{midRoman}#{smallRoman}#{midRoman}"}/, "#{smallRoman}#{largeRoman}")
    result
  end
end
