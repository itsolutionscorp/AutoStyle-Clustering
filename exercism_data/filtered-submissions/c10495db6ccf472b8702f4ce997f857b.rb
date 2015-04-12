class Hamming
    def compute(strand_one, strand_two)
        hamming_difference = 0
        i = 0
        
        while (strand_one[i] != nil && strand_two[i] != nil)
            if (strand_one[i] != strand_two[i])
                hamming_difference += 1
            end
            i += 1
        end
        
        return hamming_difference
    end
end
