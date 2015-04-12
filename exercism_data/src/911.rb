# I have no idea what I'm doing.
# Studied some programming before, now I'm
# just using a ruby reference haphazardly
# and it just so happens to work. I think.

class Hamming
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
end
