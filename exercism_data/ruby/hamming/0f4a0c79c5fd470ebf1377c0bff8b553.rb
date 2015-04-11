class Hamming
  class << self
    def compute(first, second)
      Strand.new(first).calculate_hamming Strand.new(second)
    end
  end

  class Strand
    include Enumerable

    attr_reader :values

    def initialize(string)
      @values = String(string).chars
    end

    def each(&blk)
      values.each &blk
    end

    def calculate_hamming(other)
      zip(other).inject(0) do |collector, pair|
        collector += 1 if pair.first != pair.last
        collector
      end
    end
  end
end
