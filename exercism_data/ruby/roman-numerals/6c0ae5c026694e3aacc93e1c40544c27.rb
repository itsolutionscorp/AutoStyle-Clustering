class Integer

  CONVERSION =
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

  def to_roman
    num = self
    answer = ''

    CONVERSION.each do |arabic, roman|
      quotient = num / arabic
      next if quotient == 0
      answer += (roman * quotient)
      num %= arabic
    end
    answer
  end

end
