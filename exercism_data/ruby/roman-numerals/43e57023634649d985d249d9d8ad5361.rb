class Fixnum
  def to_roman
    roman_pairs.reduce('I' * self) { |string, pair| string.gsub(*pair) }
  end

private

  def roman_pairs
    [
      %w(IIIII  V),
      %w(VV     X),
      %w(IIII  IV),
      %w(VIV   IX),
      %w(XXXXX  L),
      %w(LL     C),
      %w(XXXX  XL),
      %w(LXL   XC),
      %w(CCCCC  D),
      %w(CCCC  CD), 
      %w(DCD   CM),
      %w(DD     M)
    ]
  end
end
