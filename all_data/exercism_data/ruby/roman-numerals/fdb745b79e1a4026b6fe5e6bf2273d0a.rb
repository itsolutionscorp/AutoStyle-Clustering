require 'pry'
class Fixnum

  DECIMAL_TO_ROMAN = {
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

  def to_roman
    roman = "" 
    current = self

    DECIMAL_TO_ROMAN.each do |dec,rom|
      while current >= dec
        roman << rom
        current -= dec
      end
    end  

    roman

  end

end
