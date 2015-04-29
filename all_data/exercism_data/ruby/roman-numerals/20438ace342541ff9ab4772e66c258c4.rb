class Fixnum
  ROMAN_DIGITS = [nil, 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX']
  ROMAN_LOOKUP = 'IVXLCDM'

  def to_roman
    roman = ''
    str = to_s
    pow = str.length
    str.each_char do |c|
      pow -= 1
      r = ROMAN_DIGITS[c.to_i]
      next if r.nil?
      roman << r.tr(ROMAN_LOOKUP, ROMAN_LOOKUP[(pow * 2)..-1])
    end
    roman
  end
end
