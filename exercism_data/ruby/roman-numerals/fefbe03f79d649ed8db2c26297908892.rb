module Roman
  ROMAN_NUMERALS = %w(I V X L C D M)

  def to_roman
    return '' if zero?

    highest_digit, remaining = to_s.split(//, 2).map(&:to_i)
    place = to_s.length - 1

    highest_digit.digit_to_roman(place) + remaining.to_roman
  end

  protected

  # Roman numeral for (self * 10**place).
  # Precondition: 0 <= self <= 9
  def digit_to_roman(place)
    numerals_offset = place * 2
    one, five, ten = ROMAN_NUMERALS[numerals_offset..numerals_offset + 3]

    case self                              # Example for place==0:
    when 0..3 then one * self              # I..III
    when 4    then one + five              # IV
    when 5..8 then five + one * (self - 5) # V..VIII
    when 9    then one + ten               # IX
    else raise ArgumentError
    end
  end
end

Fixnum.send(:include, Roman)
