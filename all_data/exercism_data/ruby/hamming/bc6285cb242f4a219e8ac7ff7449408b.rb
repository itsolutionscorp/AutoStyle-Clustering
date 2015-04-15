class Hamming

  class << self

    def compute(strand_0, strand_1)
      hamming_distance(strand_0, strand_1)
    end

    def hamming_distance(strand_0,strand_1)
      calculate_distance(strand_0.chars.zip(strand_1.chars))
    end

    private

    def calculate_distance(nucleotide_pairings)
      nucleotide_pairings.inject(0) do |sum, nucleotide_pair|
        sum += add_distance_if_same(nucleotide_pair).to_i
      end
    end

    def add_distance_if_same(pair)
      return 1 if valid_pair?(pair) && increment_distance?(pair)
    end

    def valid_pair?(pair)
      !pair[0].nil? && !pair[1].nil?
    end

    def increment_distance?(pair)
      pair[0] != pair[1]
    end

  end

end
