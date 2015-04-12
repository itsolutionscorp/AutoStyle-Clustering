class Hamming
    def compute(sequence, other_sequence)
      sequence
        .each_char
        .each_with_index
        .select { |v, k| v != other_sequence[k] }
        .count
    end
end
