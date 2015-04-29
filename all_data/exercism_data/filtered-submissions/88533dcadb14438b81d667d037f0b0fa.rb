def compute(a, b)
        min_length = [a.size, b.size].min
        min_length.times.count { |index| a[index] != b[index] }
    end