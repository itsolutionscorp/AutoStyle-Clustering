def compute(str_a, str_b)
    count = [str_a.length, str_b.length].min
    res = 0
    count.times do |i|
      res +=1 if str_a[i] != str_b[i]
    end
    res
  end