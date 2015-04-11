class Fixnum
 def to_roman
    result = ""
    roman_structure = self
    roman_remap_to_arabic_numeral.map do |i, conversion|
      conversion, arabic = roman_structure.divmod(i)
      result << roman_remap_to_arabic_numeral[i] * conversion
      roman_structure = arabic
    end
    result
  end

  def roman_remap_to_arabic_numeral
    {
      1000 => 'M',
      900  => 'CM',
      500  => 'D',
      400  => 'CD',
      100  => 'C',
      90   => 'XC',
      50   => 'L',
      40   => 'XL',
      10   => 'X',
      9    => 'IX',
      5    => 'V',
      4    => 'IV',
      1    => 'I'
    }
  end
end
