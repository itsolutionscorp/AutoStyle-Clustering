class Fixnum
  def to_roman
    numerals = {
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

    i = self
    numerals.reduce("") do |ret, (k, v)|
      factor, i = i.divmod(k)
      ret << v * factor
    end
  end
end
