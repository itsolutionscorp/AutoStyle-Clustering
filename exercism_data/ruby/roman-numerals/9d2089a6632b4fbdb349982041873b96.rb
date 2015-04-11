class Integer
  def to_roman
    num_array = self.to_s.reverse.split('')
    roman = ''

    num_array.each_with_index do |num, index|
      partial = translate(num, index)
      roman = partial + roman
    end

    return roman
  end

  def translate(num, place)
    num = num.to_i
    rom_string = ''
    case place
      when 0
        one = 'I'
        five = 'V'
        ten = 'X'
      when 1
        one = 'X'
        five = 'L'
        ten = 'C'
      when 2
        one = 'C'
        five = 'D'
        ten = 'M'
      when 3
        one = 'M'
    end
    if num <= 3
      num.times do
        rom_string += one
      end
    elsif num > 3 && num < 9
      rom_string = rom_string + five
      num -= 5
      if num < 0
        num.abs.times do
          rom_string = one + rom_string
        end
      else
        num.abs.times do
          rom_string = rom_string + one
        end
      end
    else
      rom_string = rom_string + one + ten
    end
    return rom_string
  end

end
