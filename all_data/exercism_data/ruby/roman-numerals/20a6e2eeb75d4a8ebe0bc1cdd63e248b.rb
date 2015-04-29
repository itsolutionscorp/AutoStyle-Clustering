class Integer

  INT_TO_ROMAN = {
    1    => 'I',
    4    => 'IV',
    5    => 'V',
    9    => 'IX',
    10   => 'X',
    40   => 'XL',
    50   => 'L',
    90   => 'XC',
    100  => 'C',
    400  => 'CD',
    500  => 'D',
    900  => 'CM',
    1000 => 'M'
  }

  def to_roman
    num = self
    roman = ''

    while (num > 0) do
      INT_TO_ROMAN.keys.reverse.each do |int_key|
        next if num < int_key

        roman << INT_TO_ROMAN[int_key]
        num -= int_key
        break
      end
    end

    roman
  end

end
