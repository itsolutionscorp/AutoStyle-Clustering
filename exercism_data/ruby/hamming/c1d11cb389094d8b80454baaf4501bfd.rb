class Hamming
  class << self

    def compute(*seqs)
      seqs = seqs.sort_by{ |seq| seq.length }
      seqs.first.empty? || matching?(seqs) ? 0 : count_differences(seqs)
    end

    def count_differences(seqs)
      short, long = *seqs
      short.chars.zip(long.chars)
        .map{ |char1, char2| char1 == char2 ? 0 : 1 }
        .reduce(&:+)
    end

    def matching?(seqs)
      short, long = *seqs
      short == long[0...short.length]
    end

  end
end
