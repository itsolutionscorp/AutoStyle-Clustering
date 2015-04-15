class Fixnum

  ROMAN_NUMBERS = {
    1_000 => 'M',
    900   => 'CM',
    500   => 'D',
    400   => 'CD',
    100   => 'C',
    90    => 'XC',
    50    => 'L',
    40    => 'XL',
    10    => 'X',
    9     => 'IX',
    5     => 'V',
    4     => 'IV',
    1     => 'I'
  }

  def to_roman
    "".tap do |roman_string|
      self.tap do |num|
        ROMAN_NUMBERS.map do |decimal, roman|
          (num / decimal).tap do |times|
            num -= decimal* times
            roman_string << roman * times
          end
        end
      end
    end
  end
end
