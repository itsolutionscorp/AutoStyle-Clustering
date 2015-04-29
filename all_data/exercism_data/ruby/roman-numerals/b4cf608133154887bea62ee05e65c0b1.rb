class Integer

  def to_roman
    str = '' and n = self
    loop do
      if n >= 1000 then str += 'M' and n -= 1000
      elsif n >= 900 then str += 'CM' and n -= 900
      elsif n >= 500 then str += 'D' and n -= 500
      elsif n >= 400 then str += 'CD' and n -= 400
      elsif n >= 100 then str += 'C' and n -= 100
      elsif n >= 90 then str += 'XC' and n -= 90
      elsif n >= 50 then str += 'L' and n -= 50
      elsif n >= 40 then str += 'XL' and n -= 40
      elsif n >= 10 then str += 'X' and n -= 10
      elsif n >= 9 then str += 'IX' and n -= 9
      elsif n >= 5 then str += 'V' and n -= 5
      elsif n >= 4 then str += 'IV' and n -= 4
      elsif n >= 1 then str += 'I' and n -= 1
      else return str
      end
    end

  end

end
