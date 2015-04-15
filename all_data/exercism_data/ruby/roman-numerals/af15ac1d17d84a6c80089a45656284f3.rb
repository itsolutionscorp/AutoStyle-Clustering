class Fixnum
  def to_roman
    roman = ""
    number = self

    num_to_rom_dict.keys.each do |arabic|
      q, mod = number.divmod(arabic)
      roman << num_to_rom_dict[arabic] * q
      number = mod
    end
    roman
  end

private
  def num_to_rom_dict
    {
      1000 => 'M', 900 => 'CM', 500 => 'D',
      400 => 'CD', 100 => 'C', 90 => 'XC', 50 => 'L',
      40 => 'XL', 10 => 'X', 9 => 'IX', 5 => 'V',
      4 => 'IV', 1 => 'I'
    }
  end
end
