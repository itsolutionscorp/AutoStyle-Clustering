def compute(a, b)
        if a == b
            0
        end
        diffs = 0
        a.split('').each_with_index {|c, i| diffs += 1 if c != b[i] }
        diffs
    end
end