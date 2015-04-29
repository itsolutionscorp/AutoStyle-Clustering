class Roman
  MAPPING = {
    'I'  => 1,
    'IV' => 4,
    'V'  => 5,
    'IX' => 9,
    'X'  => 10,
    'XL' => 40,
    'L'  => 50,
    'XC' => 90,
    'C'  => 100,
    'CD' => 400,
    'D'  => 500,
    'CM' => 900,
    'M'  => 1000
  }
  class << self
    def convert(int)
      str = ""
      MAPPING.values.reverse.inject(int) do |remainder, divisor|
        num, remainder = remainder.divmod(divisor)
        str << (MAPPING.key(divisor)*num)
        remainder
      end
      str
    end
  end
end

# Now to monkey patch Integer
class Integer
  def to_roman
    Roman.convert(self)
  end
end
