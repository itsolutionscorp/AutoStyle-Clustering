class Fixnum
  def roman_numerals
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


  def to_roman
    remaining = self
    roman_numerals.map do |k,v|
      multiple, remainder = remaining.divmod(k)
      remaining -= k * multiple
      v * multiple
    end.join
  end
end
