class Fixnum
  def to_roman
    array = self.to_s.split(//)
    num_array = []
    array.each do |x|
      num_array.push(x.to_i)
    end
    roman_numeral=[]
    ones = num_array.pop
    if ones == 9
      roman_numeral.unshift('IX')
    elsif ones == 4
      roman_numeral.unshift('IV')
    else
      roman_numeral.unshift('V'*(ones/5) + 'I'*(ones%5))
    end

    if num_array.empty? == false
      tens = num_array.pop
      if tens == 9
        roman_numeral.unshift('XC')
      elsif tens == 4
        roman_numeral.unshift('XL')
      else
        roman_numeral.unshift('L'*(tens/5) + 'X'*(tens%5))
      end
    end

    if num_array.empty? == false
      hundreds = num_array.pop
      if hundreds == 9
        roman_numeral.unshift('CM')
      elsif hundreds == 4
        roman_numeral.unshift('CD')
      else
        roman_numeral.unshift('D'*(hundreds/5) + 'C'*(hundreds%5))
      end
    end

    if num_array.empty? == false
      thousands = num_array.pop
      roman_numeral.unshift('M'*thousands)
    end

    roman_numeral.join
  end
end
