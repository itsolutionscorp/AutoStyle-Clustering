class Fixnum



  def to_roman
    x = self
    string = ""
    roman_conversion.each do |standard, roman|
    while x >= standard
      string << roman
      x -= standard
    end
  end
string
  end

  def roman_conversion
    { 1000 => 'M',
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
