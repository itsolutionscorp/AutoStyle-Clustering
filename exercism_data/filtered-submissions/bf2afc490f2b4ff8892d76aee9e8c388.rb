def compute(a,b)

    return 0 if a == b


    h  = 0

    [a.length,b.length].min.times do |i|

      if a[i] != b[i]
        h += 1
      else
        next
      end
    end

    return h
  end