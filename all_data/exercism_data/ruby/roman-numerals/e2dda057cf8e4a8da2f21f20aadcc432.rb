class Integer
  def to_roman
    out = ''
    num_array = self.to_s.split('').map(&:to_i).reverse
    out += part_to_roman({one: 'M'}, num_array[3]) if num_array.length == 4
    out += part_to_roman({one: 'C', five: 'D', ten: 'M'}, num_array[2]) if num_array.length >= 3
    out += part_to_roman({one: 'X', five: 'L', ten: 'C'}, num_array[1]) if num_array.length >= 2
    out += part_to_roman({one: 'I', five: 'V', ten: 'X'}, num_array[0])
    out
  end

  private
  def part_to_roman(roman_layout, number)
    out_t = ''
    if number.between?(1, 3)
      number.times {out_t += roman_layout[:one]}
    elsif number == 4
      out_t = roman_layout[:one] + roman_layout[:five]
    elsif number == 5
      out_t = roman_layout[:five]
    elsif number.between?(6, 8)
      out_t = roman_layout[:five]
      (number - 5).times {out_t += roman_layout[:one]}
    elsif number == 9
      out_t = roman_layout[:one] + roman_layout[:ten]
    end
    out_t
  end

end
