class Integer
  # I - 1
  # V - 5
  # X - 10
  # L - 50
  # C - 100
  # D - 500
  # M - 1000
  def to_roman
    roman_mapping = {
      1 => ["I", "V"],
      10 => ["X", "L"],
      100 => ["C", "D"],
      1000 => ["M", ""]
    }
    self.to_s.each_char.to_a.reverse.each_with_index.inject("") do |re, (c, i)|
      c = c.to_i
      digit, next_digit = 10 ** i, 10 ** (i + 1)
      case c
      when 0...4
        roman_mapping[digit][0] * c
      when 4
        roman_mapping[digit][0] + roman_mapping[digit][1]
      when 5
        roman_mapping[digit][1]
      when 5...9
        roman_mapping[digit][1] + roman_mapping[digit][0] * (c - 5)
      when 9
        roman_mapping[digit][0] + roman_mapping[next_digit][0]
      else
        roman_mapping[next_digit][0]
      end + re
    end
  end
end
