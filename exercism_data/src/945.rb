def compute(a, b)
    len = [a.length, b.length].min
    a[0...len].chars.zip(b[0...len].chars).inject 0 do |mem, var|
      mem += var[0] == var[1] ? 0 : 1
    end
  end