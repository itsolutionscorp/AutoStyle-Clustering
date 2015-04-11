class Anagram
  attr_reader :word, :chars
  protected :word, :chars

  def initialize(word)
    @word = word.downcase
    @chars = @word.chars.sort
  end

  def match(words)
    words.select{ |w| anagram_of?(Anagram.new(w)) }
  end

  def ==(other)
    word == other.word
  end

  def anagram_of?(other)
    !(self == other) && (chars == other.chars)
  end

end
