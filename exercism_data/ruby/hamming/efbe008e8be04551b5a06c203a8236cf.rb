class Hamming

  def self.compute(dna1, dna2)
    hamming = self.new
    hamming.start(dna1, dna2)
  end

  def start(dna1, dna2)
    @dna1 = dna1
    @dna2 = dna2
    hamming_distance
  end

  private

    def hamming_distance
      @dna1.each_char.with_index.count do |_, index| 
        point_mutation?(index)
      end
    end

    def point_mutation?(index)
      if !@dna1.chars[index].nil? && !@dna2.chars[index].nil?
        @dna1.chars[index] != @dna2.chars[index]
      end
    end
end
 
