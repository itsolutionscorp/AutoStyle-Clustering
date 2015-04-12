def compute(a,b)
        raise Exception if a.length != b.length
        distance=0
        for x in 0...(a.length)
            distance = distance + 1 unless a[x] == b[x]
        end
        return distance
    end
end