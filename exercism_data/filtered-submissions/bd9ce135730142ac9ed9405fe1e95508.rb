def compute(a, b)
        min_length = [a.size, b.size].min
        (0 ... min_length).count { |index| a[index] != b[index] }
    end