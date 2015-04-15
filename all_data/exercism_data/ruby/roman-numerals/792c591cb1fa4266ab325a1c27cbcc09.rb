# solution with large pre-populated hash is below, I wanted to attempt this without using one.

class Fixnum
  def to_roman 
    number = self.to_s.reverse #reversed since it's easier than iterating backwards
    numeral = logic(number[0].to_i, "I", "V", "IX") #units - sending the numerals for 1, 5, 9 
    if number[1]
      tens = logic(number[1].to_i, "X", "L", "XC") #tens - numerals for 10, 50, 90
      numeral.insert(0, tens)
    end
    if number[2]
      hundreds = logic(number[2].to_i, "C", "D", "CM") #hundreds - numerals for 100, 500, 900
      numeral.insert(0, hundreds)
    end
    if number[3]
      thousands = th_logic(number[3].to_i)
      numeral.insert(0, thousands)
    end
    numeral
  end

  def logic (digit, one, five, nine) #same logic for 1-9, 10 - 90 & 100 - 900
    result = ""
    if digit > 4
      if digit == 9
        result << nine
        digit -= 9
      else
        result << five
        digit -= 5
      end
    end
    while digit > 0
      if digit < 4
        result << one
        digit -= 1
      else
        result << one + five
        digit -= 4
      end
    end
    result
  end

  def th_logic (digit)
    result = ""
    while digit > 0
      result << "M"
      digit -= 1
    end
    result
  end
end

#solution with the hash.
# class Fixnum

#   def to_roman
#     numerals = {
#       1 => 'I',
#       4 => 'IV',
#       5 => 'V',
#       9 => 'IX',
#       10 => 'X',
#       40 => 'XL',
#       50 => 'L',
#       90 => 'XC',
#       100 => 'C',
#       400 => 'CD',
#       500 => 'D',
#       900 => 'CM',
#       1000 => 'M'
#     }

#     result = ''
#     number = self
#     roman_numbers.reverse_each do |k,v|
#       while number >= k do
#         result << v
#         number -= k
#       end
#     end
#   result
#   end
# end
