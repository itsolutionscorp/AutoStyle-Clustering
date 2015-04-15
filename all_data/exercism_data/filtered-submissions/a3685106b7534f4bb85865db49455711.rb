def compute(a, b)
        raise "Equal length strings only" if a.length != b.length
        ham = 0
        a.length.times {|i| ham += 1 if a[i] != b[i] }
        ham
    end