class Integer < Numeric
  @@numerals = { 'M' => 1000, 'CM' => 900, 'D' => 500, 'CD' => 400, 'C' => 100, 'XC' => 90,
    'L' => 50, 'XL' => 40, 'X' => 10, 'IX' => 9, 'V' => 5, 'IV' => 4, 'I' => 1 }
  def to_roman()
    roman_numeral = ''
    number = self.to_i
    @@numerals.each do |numeral, value| 
      roman_numeral += numeral*(number/value)
      number = number % value
      if number == 0 then
        break
      end
    end
    roman_numeral
  end
end
