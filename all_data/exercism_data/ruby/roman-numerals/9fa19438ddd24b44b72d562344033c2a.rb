class Fixnum
  def to_roman
    n = self
    result = ''
    roman_mappings.each do |arabic, roman|
      while n >= arabic
        result << roman
        n -= arabic
      end
    end
    result
  end

  def roman_mappings
    {
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
end
