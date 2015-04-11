class Fixnum

  def to_roman
    result = ""
    number = self
    roman_mapping.keys.each do |i|
      target, arabic = number.divmod(i)
      result << roman_mapping[i] * target
      number = arabic
    end
    result
  end

  def roman_mapping
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
