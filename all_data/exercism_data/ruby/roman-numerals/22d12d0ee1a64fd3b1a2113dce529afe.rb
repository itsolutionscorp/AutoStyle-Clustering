class Fixnum
  
  def to_roman
    num = self.to_s.split('').map(&:to_i) 
    len = self.to_s.length
    result = String.new
    result << romanise(num[-4], 'M')
    result << romanise(num[-3], 'C', 'D', 'M')
    result << romanise(num[-2], 'X', 'L', 'C')
    result << romanise(num[-1], 'I', 'V', 'X')
    result

  end

  def romanise(t,c1,c2='',c3='')
    return "" if t == nil
    return c1+c3 if t == 9
    return c1+c2 if t == 4
    result = String.new
    if t >= 5
      result << c2
      (t-5).times { result << c1 }
    else
      t.times { result << c1 }
    end
    result
  end

end
