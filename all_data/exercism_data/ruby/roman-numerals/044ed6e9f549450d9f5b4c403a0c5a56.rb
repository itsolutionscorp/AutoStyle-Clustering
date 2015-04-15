class Fixnum

  def to_roman
    roman = ""

    self.to_s.chars.reverse.each_with_index do |x, i|
      x = x.to_i

      case i
      when 0
        if x == 9
          roman += "IX"
        elsif x == 4
          roman += "IV"
        else
          if x > 4
            roman += "V"
            x -= 5
          end
          roman += "I" * x
        end

      when 1
        if x == 9
          roman.prepend("XC")
        elsif x == 4
          roman.prepend("XL")
        else
          if x > 4
            x -= 5
            tens = "L"
          else
            tens = ""
          end
          tens += "X" * x
          roman.prepend(tens)
        end

      when 2
        if x == 9
          roman.prepend("CM")
        elsif x == 4
          roman.prepend("CD")
        else
          if x > 4
            x -= 5
            hundreds = "D"
          else
            hundreds = ""
          end
          hundreds += "C" * x
          roman.prepend(hundreds)
        end

      when 3
        thousands = "M" * x
        roman.prepend(thousands)
      end
    end

    return roman
  end

end
