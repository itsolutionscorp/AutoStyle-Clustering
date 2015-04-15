class Fixnum < Integer
  ROMANS = {
    1000  => 'M',
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
    modulus = self
    ROMANS.inject('') do |acc, (factor, roman)|
      quotient, modulus = modulus.divmod(factor)
      acc << roman * quotient
    end
  end
end
