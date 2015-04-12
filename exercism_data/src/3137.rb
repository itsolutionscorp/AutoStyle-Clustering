def compute(strand1, strand2)
        if strand1.length != strand2.length then
            raise "Error: strand length must be equal"
        end
        distance = 0
        (0..strand1.length).each do |i|
            distance += 1 if strand1[i] != strand2[i]
        end
        distance
    end