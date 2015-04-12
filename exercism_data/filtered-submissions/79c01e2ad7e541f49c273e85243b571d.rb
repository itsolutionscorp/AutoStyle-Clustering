def compute (source, copy)
        distance = 0
        source.length.times do |i|
            if source[i] != copy[i] && copy[i] != nil
                distance += 1
            end
        end
        distance
    end