def compute(strand_one, strand_two)
        min_length = [strand_one.length, strand_two.length].min

        return (0...min_length).reduce(0) do |hamming_distance, idx|
            hamming_distance += (strand_one[idx] != strand_two[idx]) ? 1 : 0
        end
    end