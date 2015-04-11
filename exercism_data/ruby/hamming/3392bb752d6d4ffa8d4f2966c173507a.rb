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
      nucleotide_pairings.count{ |nucleotide_pair| different(nucleotide_pair) }
    end

    def different(pair)
      valid_pair?(pair) && increment_distance?(pair)
    end

    def valid_pair?(pair)
      pair[0] && pair[1]
    end

    def increment_distance?(pair)
      pair[0] != pair[1]
    end

  end

end
