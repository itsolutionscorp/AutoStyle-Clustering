class Integer
  def roman
    {
      1000   => 'M', 900   => 'CM', 500   => 'D',  400   => 'CD', 100   => 'C',
                      90   => 'XC',  50   => 'L',   40   => 'XL',  10   => 'X',
                       9   => 'IX',   5   => 'V',    4   => 'IV',   1   => 'I'
    }
  end

  def to_roman
    num = self
    string = ''
    roman.each do |decimal, letter|
      while num >= decimal
        string += letter * (num / decimal)
        num -= decimal * (num / decimal)
      end
    end
    string
  end
end
