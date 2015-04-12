def compute(arga, argb)
    diff_acum = 0
    for i in 0 .. arga.length
      if arga[i]  != argb[i]
        diff_acum = diff_acum + 1
      end
    end
    diff_acum
  end