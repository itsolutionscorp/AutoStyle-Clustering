class Hamming
  class << self
    def compute(strand1, strand2)
      count_mutations(paired_nucleotides(strand1, strand2))
    end

    private

    def paired_nucleotides(strand1, strand2)
      min = [strand1.length, strand2.length].min
      strand1.chars.zip(strand2.chars).take(min)
    end

    def count_mutations(pairs)
      pairs.count { |pair| mutation?(*pair) }
    end

    def mutation?(nucleotide1, nucleotide2)
      nucleotide1 != nucleotide2
    end
  end
end
