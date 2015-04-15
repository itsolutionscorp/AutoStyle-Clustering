def compute(val1, val2)
    acum = 0
    
    for i in 0..val1.length

      if val1[i] != val2[i]
        acum = acum + 1
      end

    end

    return acum

  end