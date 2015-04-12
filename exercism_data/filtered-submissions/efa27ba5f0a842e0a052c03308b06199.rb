def compute(a, b)
        distance = 0
        size = [a.length, b.length].min
        size.times do |i|
            distance += 1 if (a[i] != b[i])
        end
        distance
    end