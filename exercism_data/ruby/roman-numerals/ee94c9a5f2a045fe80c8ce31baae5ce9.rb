class Fixnum
  ROMAN_DIGITS_VALUE = {
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
  def to_roman
    return '' if self==0
    ROMAN_DIGITS_VALUE.each do |roman, arabic|
      return roman+(self-arabic).to_roman if self>=arabic
    end
  end
end
