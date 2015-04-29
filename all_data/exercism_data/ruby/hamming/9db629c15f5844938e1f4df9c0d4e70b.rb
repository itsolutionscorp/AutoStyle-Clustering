class Hamming

  def self.compute(original, mutation)
    sequence(original, mutation).count do |strand_one, strand_two|
      comparable?(strand_one, strand_two) && mutated?(strand_one, strand_two)
    end
  end

  private

    def self.sequence(original, mutation)
      original.chars.zip(mutation.chars)
    end

    def self.mutated?(strand_one, strand_two)
      strand_one != strand_two
    end

    def self.comparable?(strand_one, strand_two)
      not(strand_one.nil? || strand_two.nil?)
    end
end
