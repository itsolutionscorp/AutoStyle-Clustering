def compute(strand1, strand2)
        numOfDifferences = 0
        # enumerating from 0 to the size of the string was ugly.
        # This method makes it a bit more clear I'm messing with
        # individual characters.
        strand1.chars.each_index do |i|
            numOfDifferences += 1 if strand1[i] != strand2[i]
        end
        numOfDifferences
    end