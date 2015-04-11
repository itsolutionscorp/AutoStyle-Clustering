class Integer
  SYMBOLS = {
    1 => 'I',
    4 => 'IV',
    5 => 'V',
    9 => 'IX',
    10 => 'X',
    40 => 'XL',
    50 => 'L',
    90 => 'XC',
    100 => 'C',
    400 => 'CD',
    500 => 'D',
    900 => 'CM',
    1000 => 'M'
  }

  def to_roman
    n = self
    SYMBOLS.to_a.reverse.inject('') do |acc, (arabic, roman)|
      div, n = n.divmod(arabic)
      acc << roman * div
    end
  end
end
