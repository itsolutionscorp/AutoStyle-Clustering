class Fixnum
  
  def to_roman
    roman = ""
    num = self.to_s
    digits = num.length

    if digits == 4
      roman << "M" * num[0].to_i
      num[0] = ""
      digits = num.length
    end
    
    if  digits == 3
      case num[0].to_i
      when 0
        roman << ""
      when 1..3
        roman << "C" * num[0].to_i
      when 4
        roman << "CD"
      when 5
        roman << "D"
      when 6..8
        roman << "D"
        roman << "C" * (num[0].to_i - 5)
      when 9
        roman << "CM"
      end
      num[0] = ""
      digits = num.length
    end
    
    if digits == 2
      case num[0].to_i
      when 0
          roman << ""
      when 1..3
        roman << "X" * num[0].to_i
      when 4
        roman << "XL"
      when 5
        roman << "L"
      when 6..8
        roman << "L"
        roman << "X" * (num[0].to_i - 5)
      when 9
        roman << "XC"
      end
      num[0] = ""
      digits = num.length
    end

    if digits == 1
      case num[0].to_i
      when 0
        roman << ""
      when 1..3
        roman << "I" * num[0].to_i
      when 4
        roman << "IV"
      when 5
        roman << "V"
      when 6..8
        roman << "V"
        roman << "I" * (num[0].to_i - 5)
      when 9
        roman << "IX"
      end
    end
    
    roman
    
  end

end
