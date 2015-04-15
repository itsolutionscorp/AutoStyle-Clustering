module Roman
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
    normal_number = self
    atomic_values.inject("") do |roman_numeral, atomic_value|
      while atomic_value <= normal_number
        normal_number -= atomic_value
        roman_numeral += VALUES[atomic_value]
      end
      roman_numeral
    end
  end

  private
  
  def atomic_values
    VALUES.keys
  end
end

class Fixnum
  include Roman
end
