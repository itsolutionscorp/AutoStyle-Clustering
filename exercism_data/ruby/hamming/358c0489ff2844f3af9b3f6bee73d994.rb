class Hamming
  def self.compute(string1, string2)
    Strings.pairs(string1, string2).count{ |pair| StringPair.new(pair).error? }
  end
end

class Strings
  def self.pairs(string1, string2)
    string1.chars.zip(string2.chars)
  end
end

class StringPair
  def initialize(pair)
    @char1, @char2 = pair[0], pair[1]
  end

  def error?
    valid_pair? && different_chars?
  end

  def valid_pair?
    @char1 && @char2
  end

  private

  def different_chars?
    @char1 != @char2
  end
end
