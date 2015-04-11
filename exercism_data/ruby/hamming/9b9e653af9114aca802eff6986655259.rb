class Hamming
  class << self
    def compute(original_strand, test_strand)
      min_size = min_strand_size(original_strand, test_strand)
      distance(normalized_strand(original_strand, min_size),
               normalized_strand(test_strand, min_size))
    end

    def distance(first_strand, second_strand)
      sequence_from(first_strand).
        each_with_index.
        reduce(0) do |accumulator, nucleotide|
          accumulator += (nucleotide[0] == second_strand[nucleotide[1]] ? 0 : 1)
      end
    end

    def sequence_from(strand)
      strand.each_char
    end

    def min_strand_size(first_strand, second_strand)
      return first_strand.size if first_strand.size <= second_strand.size
      second_strand.size
    end

    def normalized_strand(strand, size)
      strand.slice(0, size)
    end

    private :distance, :sequence_from, :min_strand_size, :normalized_strand
  end
end
