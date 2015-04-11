class Hamming

  class << self

    def compute(strand_one, strand_two)
      hamming_distance(strand_one, strand_two)
    end

    def hamming_distance(strand_one, strand_two)
      matched_pairs_by_index(strand_one, strand_two).inject(0) do |sum, pair|
        if valid_pair?(pair) && pair[0] != pair[1]
          sum += 1
        else
          sum += 0
        end
      end
    end

    def matched_pairs_by_index(strand_one, strand_two)
      strand_one.chars.zip(strand_two.chars)
    end

    def valid_pair?(matched_pair)
      !matched_pair[0].nil? && !matched_pair[1].nil?
    end

  end

end
