class Fixnum
  def to_roman
    roman_mapping = {
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
      3    => 'III',
      2    => 'II',
      1    => 'I',
    }
    value = self
    roman = ''

    roman_mapping.each_key do |r|
      repeats = (value / r).truncate
      repeats.times { roman << roman_mapping[r] }
      value = value % r
    end

    roman
  end
end
