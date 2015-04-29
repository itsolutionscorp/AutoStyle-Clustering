class DNA

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(sequence)
    Pairs.of(@strand, sequence).count(&:error?)
  end
end

class Pairs
  class << self
    def of(str1, str2)
      couples(str1,str2).map{ |couple| Pair.new(couple) }
    end

    private

    def couples(str1, str2)
      str1.chars.zip(str2.chars)
    end
  end
end

class Pair
  def initialize(couple)
    @char1, @char2 = couple[0], couple[1]
  end

  def error?
    valid_pair? and different_nucleotides?
  end

  def valid_pair?
    @char1 and @char2
  end

  private

  def different_nucleotides?
    @char1 != @char2
  end
end
