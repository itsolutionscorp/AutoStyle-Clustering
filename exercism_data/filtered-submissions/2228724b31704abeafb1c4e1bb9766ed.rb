def compute(a, b)
        # Count each pair of characters that are not equal
        # except if one is nil (happens when a is longer than b)
        a.each_char.zip(b.each_char).count { |pair|
            pair[1] != nil and pair[0] != pair[1]
        }
    end