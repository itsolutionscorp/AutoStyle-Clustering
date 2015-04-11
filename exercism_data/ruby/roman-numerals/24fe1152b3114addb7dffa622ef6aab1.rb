class Fixnum

  def to_roman
    RomanConverter.new(self).convert_to_romans
  end

end

class RomanConverter
  # I hate roman numerals
  
  attr_reader :number
  ROMANS = {1000=>"M", 900=>"CM", 500=>"D", 400=>"CD", 100=>"C", 90=>"XC", 50=>"L", 40=>"XL", 10=>"X", 9=>"IX", 5=>"V", 4=>"IV", 1=>"I"} 

  def initialize(number)
    @number = number
  end

  def convert_to_romans # solution from ProgrammingRuby, p. 354
    n = @number
    roman = ''
    ROMANS.each do |number, roman_numeral|
      count, n = n.divmod(number)
      roman << ( roman_numeral * count )
    end
    roman
  end
end

class RomanNumber
  def initialize(roman_number)
  end
end
