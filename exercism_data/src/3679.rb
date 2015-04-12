def compute(a, b)
        a = a[0, b.length-1] if a.length > b.length

        a.chars.each_with_index.inject(0) do |acc, (c, i)|
            acc + (c === b[i] ? 0 : 1)
        end
    end