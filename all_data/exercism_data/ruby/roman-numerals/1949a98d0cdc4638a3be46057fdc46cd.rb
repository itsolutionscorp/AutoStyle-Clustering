class Fixnum

  NUMERALS = { 'M' => 1000, 'CM' => 900, 'D' => 500, 'CD' => 400,
               'C' => 100,  'XC' => 90,  'L' => 50,  'XL' => 40,
               'X' => 10,   'IX' => 9,   'V' => 5,   'IV' => 4,
               'I' => 1 }

  def to_roman
    num = self
    ''.tap do |accm|
      NUMERALS.each do |numeral, value|
        accm << numeral*(num/value)
        num = num % value
      end
    end
  end
end
