class Hamming
    def compute(strand_one, strand_two)
        min_length = [strand_one.length, strand_two.length].min
        hamming_distance = 0

        for idx in 0...min_length do
            if strand_one[idx] != strand_two[idx] then
                hamming_distance += 1
            end
        end

        return hamming_distance
    end
end
