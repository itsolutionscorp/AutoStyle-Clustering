class Hamming
  class << self

    def initialize
      @strand1, @strand2 = '', ''
    end

    def compute strand1, strand2
      unify strand1, strand2
      if empty?
        0
      else
        hamming_difference
      end

    end

    private

    def unify strand1, strand2
      strand1_size, strand2_size = strand1.size, strand2.size
      @strand1, @strand2         = strand1, strand2

      if strand1_size > strand2_size
        @strand1 = strand1[0...strand2_size]
      else
        @strand2 = strand2[0...strand1_size]
      end
    end

    def empty?
      @strand1.empty? && @strand2.empty?
    end

    def hamming_difference
      result = 0

      for i in [*0...@strand1.size]
        result += 1  if @strand1[i] != @strand2[i]
      end
      result
    end

  end
end
