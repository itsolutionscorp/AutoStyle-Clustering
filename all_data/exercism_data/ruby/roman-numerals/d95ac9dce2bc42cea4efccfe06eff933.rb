class Integer

  Roman = {1 => 'I', 4 => 'IV', 5 => 'V', 9 => 'IX',
           10 => 'X', 40 => 'XL', 50 => 'L', 90 => 'XC',
           100 => 'C', 400 => 'CD', 500 => 'D', 900 => 'CM',
           1000 => 'M'}

  def to_roman
    remainder = self
    Roman.sort.reverse.each.with_object("") do |(arabic, roman), str|
      str << (roman * (remainder / arabic))
      remainder %= arabic
    end
  end
end
