class Fixnum

  @@denominations = {
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

  def to_roman
    romans = ''
    number = self
    while number > 0
      @@denominations.each do |denomination, numeral|
        if number - denomination >= 0
          number -= denomination
          romans << numeral
          break
        end
      end
    end
    romans
  end

end
