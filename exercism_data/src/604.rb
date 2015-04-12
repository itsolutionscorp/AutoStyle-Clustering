def compute(st1, st2)
    st1 = st1.split('')
    st2 = st2.split('')
    dif = 0
    [st1.length, st2.length].min.times do |i|
      dif += 1 if st1[i] != st2[i] 
    end
    dif
  end