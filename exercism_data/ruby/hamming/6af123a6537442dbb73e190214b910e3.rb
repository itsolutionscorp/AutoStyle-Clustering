class Hamming

  def self.compute(original, mutation)
    sequence(original, mutation).count do |strand_one, strand_two|
      strand_one != strand_two && !strand_one.nil? && !strand_two.nil?
    end
  end

  private

    def self.sequence(original, mutation)
      original.chars.zip(mutation.chars)
    end
end
