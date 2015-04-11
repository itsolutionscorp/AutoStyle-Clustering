class Anagram
  def initialize(word)
    @word = Word.new(word)
  end

  def match(words)
    [*words].find_all do |target|
      target = Word.new(target)
      target.is_anagram?(@word)
    end
  end
end

class Word
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def is_anagram?(other)
    has_same_letters?(other) && is_different_word?(other)
  end

  def has_same_letters?(other)
    chars == other.chars
  end

  def is_different_word?(other)
    self != other
  end

  def normalize
    word.downcase.strip
  end

  def chars
    @chars || @chars = normalize.chars.sort
  end

  def ==(other)
    normalize == other.normalize
  end

  def to_s
    word
  end
end
