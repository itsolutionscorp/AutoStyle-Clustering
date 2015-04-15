class Hamming
  def self.compute(strand_a, strand_b)
    number_of non_matching_pairs_from normalized_input(strand_a, strand_b)
  end

  class << self
    private

    def normalized_input(a, b)
      pairs_of character_arrays_from normalized_strand_strings(a,b)
    end

    def pairs_of (char_arrays)
      char_arrays.first.zip char_arrays.last
    end

    def character_arrays_from (strand_strings)
      strand_strings.map(&:chars)
    end

    def normalized_strand_strings(*strings)
      strings.sort_by(&:length)
    end

    def number_of(things)
      things.count
    end

    def non_matching_pairs_from(array_of_pairs)
      array_of_pairs.select {|pair| pair.first != pair.last }
    end
  end
end
