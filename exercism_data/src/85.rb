def compute(strand1, strand2)
        if strand1.length != strand2.length then
            raise "Error: strand length must be equal"
        end
        distance = 0
        for i in 0..strand1.length
            if strand1[i] != strand2[i] then
                distance += 1
            end
        end
        return distance
    end