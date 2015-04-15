class Integer

  ROMAN_VALUES = { 1000 => 'M',
                   900  => 'CM',
                   500  => 'D',
                   400  => 'CD',
                   100  => 'C',
                   90   => 'XC',
                   50   => 'L',
                   40   => 'XL',
                   10   => 'X',
                   9    => 'IX',
                   5    => 'V',
                   4    => 'IV',
                   1    => 'I' }

  def to_roman
    n = self
    roman_numeral = ''
    ROMAN_VALUES.each do |arabic, roman|
      roman_numeral << roman * (n/arabic)
      n = n % arabic
    end
    roman_numeral
  end
end
