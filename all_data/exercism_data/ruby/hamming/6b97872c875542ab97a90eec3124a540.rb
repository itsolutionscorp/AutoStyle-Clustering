class Hamming
  def self.compute(str1, str2)
    Pairs.characters(str1, str2).count{ |pair| CharacterPair.new(pair).error? }
  end
end

class Pairs
  def self.characters(string1, string2)
    string1.chars.zip(string2.chars)
  end
end

class CharacterPair
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
