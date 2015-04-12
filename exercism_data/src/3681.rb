def compute(a, b)
        minLength = [a.length, b.length].min;
        differences = 0;

        for i in 0..minLength-1
            if a[i] != b[i]
                differences += 1
            end
        end

        return differences;
    end