def compute(a, b)
        diff = 0;
        for i in 0..a.size-1 do
            diff += 1 if a[i] != b[i]
        end
        diff
    end