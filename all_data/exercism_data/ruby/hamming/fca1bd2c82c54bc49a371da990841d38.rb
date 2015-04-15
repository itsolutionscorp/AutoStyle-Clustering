class Hamming
  def self.compute(str1, str2)
    Pairs.of(str1, str2).count(&:error?)
  end
end

class Pairs
  class << self
    def of(str1, str2)
      pair(str1, str2).map{ |couple| Pair.new(couple) }
    end

    private

    def pair(str1, str2)
      str1.chars.zip(str2.chars)
    end
  end
end

class Pair
  def initialize(couple)
    @char1, @char2 = couple[0], couple[1]
  end

  def error?
    valid_pair? and different_chars?
  end

  def valid_pair?
    @char1 and @char2
  end

  private

  def different_chars?
    @char1 != @char2
  end
end
