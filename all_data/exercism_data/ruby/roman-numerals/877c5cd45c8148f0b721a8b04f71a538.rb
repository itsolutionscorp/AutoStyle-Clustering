class Integer < Numeric
  def to_roman
    digit_to_rom = ['I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX']
    tens_to_rom =['X', 'XX', 'XXX', 'XL', 'L','LX', 'LXX', 'LXXX', 'XC']
    hun_to_rom = ['C', 'CC', 'CCC', 'CD', 'D', 'DC', 'DCC', 'DCCC', 'CM']

    roman = 'M'* (self/1000)
    x = self%1000/100
    roman += hun_to_rom[x-1] if x!=0
    x = self%100/10
    roman += tens_to_rom[x-1] if x!=0
    roman += digit_to_rom[(self%10)-1] if (self%10)!=0
    roman
  end
end
