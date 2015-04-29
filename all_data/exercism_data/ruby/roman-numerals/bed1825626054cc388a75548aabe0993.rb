class Fixnum
  def to_roman
    roman_ref = {
      "1" => "I",
      "4" => "IV",
      "5" => "V",
      "9" => "IX",
      "10" => "X",
      "40" => "XL",
      "50" => "L",
      "90" => "XC",
      "100" => "C",
      "400" => "CD",
      "500" => "D",
      "900" => "CM",
      "1000" => "M"
    }
    roman = ""
    num_arr = self.to_s.chars
    roman_arr = num_arr.map.with_index do |d, i|
      num = ""
      order = 10 ** (num_arr.size - (i + 1))
      chunk = d.to_i * order
      if roman_ref.has_key?(chunk.to_s)
        num = roman_ref[chunk.to_s]
      else
        # If digit is between 5 and 10, subtract 5 * the order of magnitude from the remaining amount and add the corresponding '5' numeral to the result.
        if d.to_i > 5
          d = (d.to_i - 5).to_s
          chunk -= 5 * order
          num = roman_ref[(5 * order).to_s]
        end
        d.to_i.times { num += roman_ref[(chunk / d.to_i).to_s] }
      end
      num
    end
    roman_arr.join
  end
end
