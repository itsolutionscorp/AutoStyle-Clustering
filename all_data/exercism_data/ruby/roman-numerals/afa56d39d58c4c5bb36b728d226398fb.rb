class Fixnum
  def to_roman
    i = self
    s = ""
    roman_hash =
      {
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
    roman_hash.each do |key, value|
      while i >= key
        s << value
        i -= key
      end
    end
    s
  end
end
