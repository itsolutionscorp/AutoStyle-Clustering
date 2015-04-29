class Integer
  def to_roman
    return '' unless self > 0
    #Return the largest matching key-value pair
    match = CONVERSION.detect { |roman, arabic| self >= arabic }
    #Populate the result with a Roman Numeral match[0],
    #and parse the remainder of the number
    match[0] + (self - match[1]).to_roman
  end

  CONVERSION =
  {
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
end
