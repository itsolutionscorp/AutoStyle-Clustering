def compute(string_1, string_2)
        hamming_distance = 0
        
        effective_sequence_length = [string_1.size, string_2.size].min
        for index in 0..(effective_sequence_length - 1)
            hamming_distance += 1 if string_1[index] != string_2[index]
        end
        
        return hamming_distance
    end