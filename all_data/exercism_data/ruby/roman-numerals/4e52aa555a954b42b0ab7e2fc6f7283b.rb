class Integer
  MAPPING = {
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
    1 => 'I' }

  def to_roman
    current = self
    result = ''

    while current > 0
      arabic, roman = MAPPING.find{|i, _| current >= i }
      current -= arabic
      result += roman
    end

    result
  end
end
