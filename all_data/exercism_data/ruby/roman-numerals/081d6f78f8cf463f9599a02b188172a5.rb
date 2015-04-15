class Fixnum

  def numerals 
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
      1    => 'I',
    }
  end
  
  def to_roman
    letter = ""
    n = self
    numerals.each_key {|key| letter << numerals[key]*n.divmod(key)[0] 
      n=n.divmod(key)[1]}
    letter
  end
end
