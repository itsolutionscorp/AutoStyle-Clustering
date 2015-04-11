class Fixnum
  def to_roman
    number = self
    string = ''

    roman_dictionary.each do |arabic, roman|
      while number >= arabic
         string += roman
         number -= arabic
       end 
    end

    string
  end

  def roman_dictionary
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
