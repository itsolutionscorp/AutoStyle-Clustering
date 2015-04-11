class Fixnum
  def to_roman
    remaining = self
    ROMANS.each_with_object('') do |(arabic, roman), result|
      if remaining >= arabic
        times, remaining = remaining.divmod(arabic)
        result << roman * times
      end
    end
  end

  ROMANS = { 
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
end
