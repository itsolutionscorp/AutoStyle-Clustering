def compute(strand1, strand2)
        numOfDifferences = 0



        strand1.chars.each_index do |i|
            numOfDifferences += 1 if strand1[i] != strand2[i]
        end
        numOfDifferences
    end