class Hamming

  def initialize(strand, mutation)
    @strand = strand[0...mutation.length]
    @mutation = mutation[0...strand.length]
  end

  def compute
    strand.chars.each_with_index.inject(0) do |acc, (char, index)|
      acc += difference(char, mutation[index])
    end
  end

  def self.compute(strand, mutation)
    hamming = Hamming.new(strand, mutation)
    hamming.compute
  end

  private
    attr_accessor :strand, :mutation

    def difference(char1, char2)
      return 0 if char1 == char2
      return 1
    end
end
