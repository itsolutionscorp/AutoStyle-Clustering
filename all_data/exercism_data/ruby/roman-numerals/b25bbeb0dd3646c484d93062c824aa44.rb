class Fixnum

  def to_roman
    num = self
    roman_mappings.each_with_object("") do |(arabic, roman), str|
      while num >= arabic
        str << roman
        num -= arabic
      end
    end

  end

private
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
