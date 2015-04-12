def compute(a, b)
        distance = 0
        overflow = 0
        if (a.length > b.length)
            overflow = a.length - b.length
            last_index = b.length - 1
        elsif (b.length > a.length)
            overflow = b.length - a.length
            last_index = a.length - 1
        else
            last_index = a.length
        end
        
        (0..last_index).each {|n|
            if (a[n] != b[n])
                distance += 1
            end
        }
        distance
    end