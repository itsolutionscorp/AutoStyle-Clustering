def compute(original, mutated)
        hamming_distance = 0
        strand_length = [original.length, mutated.length].min
        (0...strand_length).each do |nucleotide|
            hamming_distance += 1 if original[nucleotide] != mutated[nucleotide]
        end

        hamming_distance
    end