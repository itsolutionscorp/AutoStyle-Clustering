class Hamming

  attr_accessor :small_strand, :large_strand

  class << self

    def compute(strand_a, strand_b)
      @small_strand, @large_strand = strand_a, strand_b
      count_mutations
    end

    private

    def strand_length
      [@small_strand.length, @large_strand.length].min
    end

    def count_mutations
      mutation_count = 0
      strand_length.times { |i| mutation_count += 1 unless @small_strand[i] == @large_strand[i] }
      mutation_count
    end

  end

end
