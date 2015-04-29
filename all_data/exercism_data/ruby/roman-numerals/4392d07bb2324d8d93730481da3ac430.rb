class Integer

  ROMAN_CONVERSION = { 
    1    => 'I',
    4    => 'IV',
    5    => 'V',
    9    => 'IX',
    10   => 'X',
    40   => 'XL',
    50   => 'L',
    90   => 'XC',
    100  => 'C',
    400  => 'CD',
    500  => 'D',
    900  => 'CM',
    1000 => 'M',
  }

  def to_roman
    num = self
    ROMAN_CONVERSION.keys.sort.reverse.reduce("") do |roman, arabic|
      (num / arabic).times { roman << ROMAN_CONVERSION[arabic] }
      num %= arabic
      roman
    end
  end
end
