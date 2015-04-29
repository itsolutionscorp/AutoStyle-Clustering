class Hamming
  class << self
    def compute(strand_a, strand_b)
      zip_strands(strand_a, strand_b).count { |a, b| a != b }
    end

    private

    def zip_strands(strand_a, strand_b)
      strand_a.chars.zip(strand_b.chars)
    end
  end
end
