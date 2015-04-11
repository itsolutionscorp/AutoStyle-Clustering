class Fixnum
  RN = {
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
    roman_letter, roman_value = RN.find { |_k, v| v <= self }
    if roman_value == self
      roman_letter
    else
      q, m = divmod(roman_value)
      m == 0 ? (roman_letter * q) : (roman_letter * q) + m.to_roman
    end unless self > 3000
  end
end
