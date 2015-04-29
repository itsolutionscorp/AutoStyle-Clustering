class Integer
  def to_roman # 3
    roman_symbols = {
      1    => 'I',
      2    => 'II',
      3    => 'III',
      4    => 'IV',
      5    => 'V',
      6    => 'VI',
      7    => 'VII',
      8    => 'VIII',
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

    result = []
    count = self
    until count <= 0
      highest = roman_symbols.select { |decimal, roman| decimal <= count }
      amount = highest.to_a.last[0]
      result.push(roman_symbols[amount])
      count -= amount
    end
    result.join
  end
end
