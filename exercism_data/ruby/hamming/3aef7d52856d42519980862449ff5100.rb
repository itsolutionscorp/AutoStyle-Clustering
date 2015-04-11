class Hamming
  class << self
    def compute(first, second)
      Strand.new(first).calculate_hamming Strand.new(second)
    end
  end

  class Strand
    include Enumerable

    attr_reader :nucleotides

    def initialize(string)
      @nucleotides = String(string).chars
    end

    def each(&blk)
      nucleotides.each &blk
    end

    def calculate_hamming(other)
      zip(other).count { |pair| pair.first != pair.last }
    end
  end
end
