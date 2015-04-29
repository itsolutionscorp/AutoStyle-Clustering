class Fixnum

  def deci_to_roman_hash
    {
      1000=> 'M',
      900 => 'CM',
      500 => 'D',
      400 => 'CD',
      100 => 'C',
      90  => 'XC',
      50  => 'L',
      40  => 'XL',
      10  => 'X',
      9   => 'IX',
      5   => 'V',
      4   => 'IV',
      1   => 'I'
    }
  end

  def to_roman
    base_num = self
    deci_to_roman_hash.reduce "" do |roma, (val, lite)|
      multi, base_num = base_num.divmod val
      roma + lite * multi
    end
  end
end
