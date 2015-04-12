def compute(a, b)
        pairs = a.chars.to_a.zip(b.chars.to_a)

        difference = 0
        pairs.each do |pair|
            next unless pair[0] && pair[1]
            difference += 1 unless pair[0].upcase == pair[1].upcase
        end
        return difference
    end