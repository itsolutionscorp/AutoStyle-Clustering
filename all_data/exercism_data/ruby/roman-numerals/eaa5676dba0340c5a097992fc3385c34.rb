class Fixnum

  NUMERALS = {
    'M'  => 1000,
    'CM' => 900,
    'D'  => 500,
    'CD' => 400,
    'C'  => 100,
    'XC' => 90,
    'L'  => 50,
    'XL' => 40,
    'X'  => 10,
    'IX' => 9,
    'V'  => 5,
    'IV' => 4,
    'I'  => 1
  }

  def to_roman
    number  = self
    numeral = ''

    NUMERALS.each do |symbol, value|
      while number >= value
        numeral << symbol
        number -= value
      end
    end
    numeral
  end
end
