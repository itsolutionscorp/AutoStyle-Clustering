class Integer
  ROMAN_NUMERALS = {
    1000 => 'M',
     900 => 'CM',
     500 => 'D',
     400 => 'CD',
     100 => 'C',
      90 => 'XC',
      50 => 'L',
      40 => 'XL',
      10 => 'X',
       9 => 'IX',
       5 => 'V',
       4 => 'IV',
       1 => 'I',
  }

  def to_roman
    return '' if self == 0
    ROMAN_NUMERALS.each do |int, roman|
      return roman + (self - int).to_roman if self >= int
    end
  end
end
