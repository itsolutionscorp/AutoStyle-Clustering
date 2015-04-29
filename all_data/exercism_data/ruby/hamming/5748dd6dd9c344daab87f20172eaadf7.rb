class Hamming
  class << self
    def compute(first, second)
      Strand.new(first).calculate_hamming Strand.new(second)
    end
  end

  class Strand
    attr_reader :values

    def initialize(string)
      @values = String(string).chars
    end

    def calculate_hamming(other)
      hamming = 0
      values.each_with_index do |char, idx|
        hamming += 1 if other.values[idx] != char
      end
      hamming
    end
  end
end
