class Fixnum

  def to_roman
    i = self
    romans = ''
    roman_mappings.each do |number, numeral|
      while i >= number
        romans << numeral
        i -= number
      end
    end
    romans
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
