class Hamming
  class << self

    def compute(*seqs)
      seqs = seqs.sort_by{ |seq| seq.length }
      seqs[0].empty? ? 0 : count_differences(seqs)
    end

    def count_differences(seqs)
      seqs[0].chars.zip(seqs[1].chars)
        .map{ |char1, char2| char1 == char2 ? 0 : 1 }
        .reduce(&:+)
    end

  end
end
