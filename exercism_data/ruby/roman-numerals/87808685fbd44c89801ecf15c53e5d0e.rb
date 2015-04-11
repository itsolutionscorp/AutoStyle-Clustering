class Fixnum
  ROMAN_CONVERSIONS = {
    1 => 'I',
    5 => 'V',
    10 => 'X'
  }

  def to_roman
    ROMAN_CONVERSIONS[self]
  end
end
