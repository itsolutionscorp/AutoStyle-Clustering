class Integer

  def to_roman

    i = self
    
    ARABIC_TO_ROMAN_MAPPINGS.each.with_object('') do |(arabic, roman), s|
      while i >= arabic do
        s << roman
        i -= arabic
      end
      s
    end
  end

  ARABIC_TO_ROMAN_MAPPINGS = { 
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
    1 => 'I' 
  }
    
end
