class Integer
  ROMAN_VALS = {
    1000 => 'M',
    500 => 'D',
    100 => 'C',
    50 => 'L',
    10 => 'X',
    5 => 'V',
    1 => 'I'
  }
  REDUCTIONS = {
    1000 => 100,
    500 => 100,
    100 => 10,
    50 => 10,
    10 => 1,
    5 => 1,
    1 => 0
  }

  def to_roman
    return ROMAN_VALS[self] if ROMAN_VALS.has_key?(self)
    REDUCTIONS.each do |k, v|
      return k.to_roman + (self - k).to_roman if self > k
      return v.to_roman + (self + v).to_roman if self >= k - v and self < k
    end
  end
end
