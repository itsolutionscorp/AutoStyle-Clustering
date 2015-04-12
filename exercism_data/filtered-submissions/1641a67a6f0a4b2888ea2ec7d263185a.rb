def compute(a,b)
        c = 0
        a.length.times { |i| c += 1 if a[i] != b[i] }

        c
    end