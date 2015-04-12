def compute(strand1, strand2)
        numOfDifferences = 0
        # Apparently for loops are bad. I agree.
        (0..strand1.size).each do |i|
            numOfDifferences += 1 if strand1[i] != strand2[i]
        end
        return numOfDifferences
    end