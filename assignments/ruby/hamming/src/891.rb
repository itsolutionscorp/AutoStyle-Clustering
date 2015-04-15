def compute(strand1, strand2)
        numOfDifferences = 0
        i = 0
        for nucleotide in 0..strand1.length
            if strand2[i] != strand1[i]
                numOfDifferences = numOfDifferences + 1
            end
            i = i + 1
        end
        return numOfDifferences
    end