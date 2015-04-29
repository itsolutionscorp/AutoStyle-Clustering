class Fixnum

  def to_roman(*n)
    num = self
    numerals = num.to_s.split('')
    translated = ""
    numerals_count = numerals.count
    numerals.each do |n|
      n = n.to_i
      if numerals_count == 4 && n > 0
        translated += "M" * n
      end
      if numerals_count == 3 && n > 0
        if n < 4
          translated += "C" * n
        elsif n == 4
          translated += "CD"
        elsif n == 5
          translated += "D"
        elsif n > 5 && n < 9
          translated += "D"
          translated += "C" * (n - 5)
        elsif n == 9
          translated += "CM"
        end
      elsif numerals_count == 2 && n > 0
        if n < 4
          translated += "X" * n
        elsif n == 4
          translated += "XL"
        elsif n == 5
          translated += "L"
        elsif n > 5 && n < 9
          translated += "L"
          translated += "X" * (n - 5)
        elsif n == 9
          translated += "XC"
        end
      elsif numerals_count == 1 && n > 0
        if n < 4
          translated += "I" * n
        elsif n == 4
          translated += "IV"
        elsif n == 5
          translated += "V"
        elsif n > 5 && n < 9
          translated += "V"
          translated += "I" * (n - 5)
        elsif n == 9
          translated += "IX"
        end
      end
      numerals_count -= 1
    end
    return translated
  end
  
end
