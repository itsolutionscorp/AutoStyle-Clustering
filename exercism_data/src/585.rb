def compute(a, b)


        a.each_char.zip(b.each_char).count { |pair|
            pair[1] != nil and pair[0] != pair[1]
        }
    end