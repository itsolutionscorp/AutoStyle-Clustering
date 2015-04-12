def compute(strand1, strand2)
        length = 0
        differences = 0

        l1 = strand1.length
        l2 = strand2.length
            
        if l1 <= l2 then length = l1 else length = l2 end

        length.times do |i|
            differences += 1 if strand1[i] != strand2[i]
        end

        differences
    end