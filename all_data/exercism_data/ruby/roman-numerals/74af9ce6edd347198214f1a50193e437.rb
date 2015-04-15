class Integer
  ROMAN_TABLE = {
    'I' => 1,
    'IV'=> 4,
    'V' => 5,
    'IX'=> 9,
    'X' => 10,
    'XL'=> 40,
    'L' => 50,
    'XC'=> 90,
    'C' => 100,
    'CD'=> 400,
    'D' => 500,
    'CM'=> 900,
    'M' => 1000,
  }

  def to_roman
    rev_table = ROMAN_TABLE.keys.reverse
    remaining = self
    out = ''
    rev_table.each_with_index do |key,index|
      count = remaining / ROMAN_TABLE[key]
      old_remaining = remaining
      remaining = remaining % ROMAN_TABLE[key]

      out += (key * count)
    end
    out
  end
end
