module Roman
  ROMAN_NUMERALS = {
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
    remainder = self

    ROMAN_NUMERALS.map {|numeral, divisor|
      quotient, remainder = remainder.divmod(divisor)
      numeral * quotient
    }.join
  end
end

class Integer
  include Roman
end
