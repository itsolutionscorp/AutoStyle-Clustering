class Fixnum

  def roman_mapping
    {
      'M' => 1000,
       'CM' => 900,
       'D' => 500,
       'CD' => 400,
       'C' => 100,
       'XC' => 90,
       'L' => 50,
       'XL' => 40,
       'X' => 10,
       'IX' => 9,
       'V' => 5,
       'IV' => 4,
       'I' => 1
    }
  end

  def to_roman
    number_string = ""
    number = self
    roman_mapping.each do |key, divisor|
      quotient, modulus = number.divmod(divisor)
      number = modulus
      number_string << key * quotient
    end
    number_string
  end
end
