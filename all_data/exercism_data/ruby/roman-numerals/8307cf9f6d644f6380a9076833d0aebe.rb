class Integer < Numeric
  def to_roman
    num = self
    roman_mappings = {
      'M' => 1000,
      'CM' => 900,
      'D' => 500,
      'CD' => 400,
      'C' => 100,
      'XC' => 90,
      'L' => 50,
      'XL' => 40,
      'X' => 10,
      'IX' => 9,
      'V' => 5,
      'IV' => 4,
      'I' => 1
    }
    str = ""

    roman_mappings.each_pair do |rom_str, rom_mod|
      while num / rom_mod > 0 do
        str << rom_str
        num -= rom_mod
      end
    end
    str
  end
end
