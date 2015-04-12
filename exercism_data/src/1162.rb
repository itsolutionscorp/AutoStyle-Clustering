def compute(a,b)
    errors = 0

    [a.length,b.length].min.times do |i|
      errors +=1 if a[i] != b[i]
    end

    errors

  end