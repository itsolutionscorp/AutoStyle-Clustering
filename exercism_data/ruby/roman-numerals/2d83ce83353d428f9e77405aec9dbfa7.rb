class Integer < Numeric
  @@numerals = { 'M' => 1000, 'CM' => 900, 'D' => 500, 'CD' => 400, 'C' => 100, 'XC' => 90,
    'L' => 50, 'XL' => 40, 'X' => 10, 'IX' => 9, 'V' => 5, 'IV' => 4, 'I' => 1 }
  def to_roman()
    number = self
    @@numerals.each_with_object('') do |(numeral, value), roman_numeral| 
      rep, number = number.divmod(value)
      roman_numeral << numeral * rep
    end
  end
end
