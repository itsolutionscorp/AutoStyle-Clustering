class Hamming

    def self.compute(strand_one, strand_two)
      hamming_distance(strand_one, strand_two)
    end

    def self.hamming_distance(strand_one, strand_two)
      matched_pairs_by_index(strand_one, strand_two).select do |pair|
        valid_pair?(pair) && pair[0] != pair[1]
      end.count
    end

    def self.matched_pairs_by_index(strand_one, strand_two)
      strand_one.chars.zip(strand_two.chars)
    end

    def self.valid_pair?(matched_pair)
      matched_pair[0] && matched_pair[1]
    end

end
