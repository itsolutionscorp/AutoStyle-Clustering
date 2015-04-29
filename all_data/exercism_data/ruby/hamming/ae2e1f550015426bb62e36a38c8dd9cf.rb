class Hamming
  class << self

    def compute(a, b)
      nucleotide_pairs(a, b).inject(0) do |mutations, pair|
        did_pair_mutate(pair) ? mutations += 1 : mutations
      end
    end

  private

    def nucleotide_pairs a, b
      a.chars.zip(b.chars)
    end

    def did_pair_mutate(pair)
      return false if pair.last.nil?
      return true if pair.first != pair.last
    end

  end
end
