def compute(strand1, strand2)

        ham_dif = 0

        shorterString = strand1
        if (strand1.length > strand2.length)
            shorterString = strand2
        end

        for i in 0..shorterString.length-1
            unless strand1[i] == strand2[i]
                ham_dif+=1
            end

        end
        ham_dif
    end