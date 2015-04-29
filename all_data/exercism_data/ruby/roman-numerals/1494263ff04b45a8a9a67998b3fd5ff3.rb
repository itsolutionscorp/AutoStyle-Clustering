class Integer
  ROMAN_TABLE = {
    1000 => 'M',
    900 => 'CM',
    500 => 'D',
    400 => 'CD',
    100 => 'C',
    90 => 'XC',
    50 => 'L',
    40 => 'XL',
    10 => 'X',
    9 => 'IX',
    5 => 'V',
    4 => 'IV',
    1 => 'I'
  }

  def to_roman
    ret = ''
    num = self
    ROMAN_TABLE.keys.each do |key|
      quotient, mod = num.divmod(key)
      ret << ROMAN_TABLE[key] * quotient
      num = mod
    end
    ret
  end
end
