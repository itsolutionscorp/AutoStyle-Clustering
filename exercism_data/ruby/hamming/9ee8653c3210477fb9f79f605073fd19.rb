class Hamming

  attr_accessor :small_strand, :large_strand

  class << self

    def compute(strand_a, strand_b)
      @small_strand, @large_strand = strand_a, strand_b
      determine_size
      count_mutations(@small_strand.chars, @large_strand.chars)
    end

    private

    # this shouldnt matter
    def determine_size
      small_strand, large_strand = @small_strand, @large_strand
      if @small_strand.size > @large_strand.size
        @small_strand, @large_strand = large_strand, small_strand
      end
    end

    def count_mutations(small_strand, large_strand)
      mutation_count = 0
      small_strand.each_with_index { |mutation, index| mutation == large_strand[index] ? 0 : mutation_count += 1 }
      mutation_count
    end

  end

end
