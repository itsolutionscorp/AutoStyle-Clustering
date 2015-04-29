class Hamming
  def self.compute(strand_a, strand_b)
    stats = HammingComputationStats.new(strand_a, strand_b)

    (0..stats.max_length).inject(0) do |sum, index|
      strand_a[index] == strand_b[index] ? sum : sum + 1
    end
  end

  private

  class HammingComputationStats < Struct.new(:strand_a, :strand_b)
    def max_length
      ([strand_a.length, strand_b.length].min)-1
    end
  end
end
