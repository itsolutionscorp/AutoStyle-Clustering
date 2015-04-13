def compute(s1, s2)
        l = [s1.length, s2.length].min
        s1[0..l-1].each_char.zip(s2[0..l-1].each_char).select { |a,b| a != b }.size
    end