class Fixnum

  def to_roman
    answer = []
    num_string = self.to_s

      case num_string[-4]
      when "1" then answer << "M"
      when "2" then answer << "MM"
      when "3" then answer << "MMM"
      when "4" then answer << "MMMM"
      end

      case num_string[-3]
      when "1" then answer << "C"
      when "2" then answer << "CC"
      when "3" then answer << "CCC"
      when "4" then answer << "CD"
      when "5" then answer << "D"
      when "6" then answer << "DC"
      when "7" then answer << "DCC"
      when "8" then answer << "DCCC"
      when "9" then answer << "CM"
      end

      case num_string[-2]
      when "1" then answer << "X"
      when "2" then answer << "XX"
      when "3" then answer << "XXXX"
      when "4" then answer << "XL"
      when "5" then answer << "L"
      when "6" then answer << "LX"
      when "7" then answer << "LXX"
      when "8" then answer << "LXXX"
      when "9" then answer << "XC"
      end

      case num_string[-1]
      when "1" then answer << "I"
      when "2" then answer << "II"
      when "3" then answer << "III"
      when "4" then answer << "IV"
      when "5" then answer << "V"
      when "6" then answer << "VI"
      when "7" then answer << "VII"
      when "8" then answer << "VIII"
      when "9" then answer << "IX"
      end
    answer.join
  end
end
