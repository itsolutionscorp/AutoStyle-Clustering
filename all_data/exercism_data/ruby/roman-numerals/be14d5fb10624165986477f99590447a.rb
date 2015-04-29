class Fixnum
  def to_roman

    #set up two arrays respectively containing the roman representation of
    #ones and fives at each position in the number.
    ones  = ['M', 'C', 'X', 'I']
    fives = ['' , 'D', 'L', 'V']

    #check 0 < Fixnum <= 4,999
    return "Number out of range" if (self < 1 || self > 4999)

    #prepare a clean output string
    output = ""

    #convert input into a 4-digit string (with leading zeros) and iterate thru the characters.
    #4 and 9 are special cases. Then, if a digit is > 5 output '5-character' and subtract 5.
    #Then output remaining 1s.

    self.to_s.rjust(4, "0").chars.each_with_index do |digit_str, position|
      digit = digit_str.to_i
      if digit == 9
        output << ones[position] << ones[position-1]
      elsif digit == 4
        output << ones[position] << fives[position]
      else
        if digit >= 5
          output << fives[position]
          digit -= 5
        end
        digit.times { output << ones[position] }
      end
    end
    output
  end
end
