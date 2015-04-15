class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def ===(other)
    normalize == other.normalize
  end

  def match(words)
    words.select { |possibility| Anagram.new(possibility) === self }
  end

  protected

  def normalize
    @word.chars.sort.join
  end
end
