class Fixnum
  ROMANS = {
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
    num = self

    ROMANS.reduce('') do |result, (symbol, value)|
      x = num / value
      num -= x * value
      result + symbol * x
    end
  end
end
