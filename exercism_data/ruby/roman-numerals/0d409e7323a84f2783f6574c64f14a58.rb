class Fixnum

  NUMERALS = { 
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
    6 => 'VI',
    5 => 'V',
    4 => 'IV',
    1 => 'I'
  }

  def to_roman
    n = self
    result = ''

    NUMERALS.each do |k,v|
      while n >= k
        result << v
        n -= k
      end
    end
    result
  end

end
