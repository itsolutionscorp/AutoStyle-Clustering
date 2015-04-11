# roman.rb
class Integer
  def to_roman
    n = self
    roman_map.each_with_object('') do | (i, r), s |
      s << r * (n / i)
      n = n % i
    end
  end

  private

  def roman_map
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
