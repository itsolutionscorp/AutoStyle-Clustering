class Fixnum

  def romans
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

  def to_roman
    num = self
    result = ''
    romans.each do |k,v|
      while num >= k
        result << v
        num -= k
      end
    end
    result
  end

end
