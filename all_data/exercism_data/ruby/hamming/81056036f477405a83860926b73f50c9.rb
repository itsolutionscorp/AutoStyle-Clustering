class Hamming

  attr_accessor :strand_a, :strand_b

  class << self

    def compute(strand_a, strand_b)
      @strand_a, @strand_b = strand_a, strand_b
      count_mutations
    end

    private

    def strand_length
      [@strand_a.length, @strand_b.length].min
    end

    def count_mutations
      mutation_count = 0
      strand_length.times { |i| mutation_count += 1 unless @strand_a[i] == @strand_b[i] }
      mutation_count
    end

  end

end
