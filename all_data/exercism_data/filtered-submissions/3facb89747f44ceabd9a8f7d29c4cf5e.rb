def compute(a, b)
        diffs = 0
        a.split('').each_with_index {|c, i| diffs += 1 if c != b[i] }
        diffs
    end