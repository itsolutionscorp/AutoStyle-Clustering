def compute(strand1, strand2)

        ham_dif = 0

        shorterString = (strand1.length > strand2.length) ? strand2 : strand1

        shorterString.length.times do |i|
            unless strand1[i] == strand2[i]
                ham_dif+=1
            end
        end
        
        ham_dif
    end