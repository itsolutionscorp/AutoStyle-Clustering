class Fixnum
  ROMAN_CHARS = "IVXLCDM"
  ROMAN_DIGITS = %w(
    a
    aa
    aaa
    ab
    b
    ba
    baa
    baaa
    ac
  )

  def to_roman
    roman, pos, cur = "", 0, self
    while cur > 0
      number = cur % 10
      unless number.zero?
        roman = ROMAN_DIGITS[number - 1].tr("abc", ROMAN_CHARS[pos, 3]) + roman
      end
      cur /= 10
      pos += 2
    end
    roman
  end
end
