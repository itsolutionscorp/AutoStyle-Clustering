DECIMAL_TO_ROMAN = {
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

class Integer
  def to_roman
    value = self
    DECIMAL_TO_ROMAN.map { |decimal, roman|
      modulus, value = value.divmod decimal
      roman * modulus
    }.join
  end
end
