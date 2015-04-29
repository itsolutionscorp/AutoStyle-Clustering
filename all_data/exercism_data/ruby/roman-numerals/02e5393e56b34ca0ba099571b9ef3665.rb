class Fixnum

  def to_roman
    num = self
    output = ""
    roman_numerals = {
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

    roman_numerals.each do |letter, value|
      while num >= value
        num -= value
        output << letter
      end
    end
    output
  end

end
