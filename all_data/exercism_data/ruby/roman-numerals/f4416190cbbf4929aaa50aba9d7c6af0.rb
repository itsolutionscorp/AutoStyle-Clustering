class Fixnum
  
  def to_roman
    num = self 
    result = String.new
    thp = num / 1000
    num = num - thp * 1000
    hp = num / 100
    num = num - hp * 100
    tp = num / 10
    num = num - tp * 10
    up = num / 1
    result << romanise(thp, 'M')
    result << romanise(hp, 'C', 'D', 'M')
    result << romanise(tp, 'X', 'L', 'C')
    result << romanise(up, 'I', 'V', 'X')
    result

  end

  def romanise(t,c1,c2='',c3='')
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
