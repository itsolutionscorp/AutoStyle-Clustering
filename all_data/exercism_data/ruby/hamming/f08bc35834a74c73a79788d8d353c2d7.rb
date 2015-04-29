class Hamming
  def self.compute(str1, str2)
    Pairs.of(str1, str2).count(&:error?)
  end
end

class Pairs
  def self.of(str1, str2)
    str1.chars.zip(str2.chars).map{ |pair| Pair.new(pair) }
  end
end

class Pair
  def initialize(pair)
    @char1, @char2 = pair[0], pair[1]
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
