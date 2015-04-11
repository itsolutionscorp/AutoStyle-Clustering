class Fixnum
  VALUES = {
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
    result = ""
    remaining = self
    VALUES.each do |value, roman|
      while remaining >= value
        result << roman
        remaining -= value
      end
    end
    result
  end
end
