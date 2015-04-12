class Hamming
    def Hamming.compute(string1, string2)
        hamm = 0
        
        shortest = string1
        if string1.length > string2.length then
            shortest = string2
        end

        for i in 0..shortest.length - 1
            if string1[i] != string2[i]
                hamm += 1
            end
        end

        return hamm
    end
end
