class Fixnum
  @@val = {
    1000 => 'M',
    500 => 'D',
    100 => 'C',
    50 => 'L',
    10 => 'X',
    5 => 'V',
    1 => 'I'
  }
  def f(big, i, ret)
    small = 10**((big-1).to_s.length - 1)

    if ((big - small) <= i) and (i < big)
        ret += @@val[small]
        i += small
    end

    ret += @@val[big] * (i / big)
    i -= big * (i / big)

    return ret, i
  end
  def to_roman
    ret, i = f(1000, self, "")
    ret, i = f(500, i, ret)
    ret, i = f(100, i, ret)
    ret, i = f(50, i, ret)
    ret, i = f(10, i, ret)
    ret, i = f(5, i, ret)
    ret += 'I' * i
  end
end
