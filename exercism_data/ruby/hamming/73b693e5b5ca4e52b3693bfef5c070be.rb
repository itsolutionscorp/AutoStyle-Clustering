module Hamming
  def self.compute a, b
    DNA.new(a).ham_with DNA.new(b)
  end

  class DNA
    def initialize sequence
      @sequence = sequence.split('')
    end

    def ham_with other
      diff(other).size
    end

    def diff other
      o_indexed = other.indexed
      indexed[0...o_indexed.size] - o_indexed
    end

    protected

    def indexed
      @sequence.each_with_index.map {|g, i| g + i.to_s}
    end
  end
end
