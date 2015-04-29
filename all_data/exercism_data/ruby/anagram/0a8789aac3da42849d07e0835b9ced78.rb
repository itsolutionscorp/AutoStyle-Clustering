class Anagram
  def initialize(word)
    @word = Word.new(word)
  end

  def match(words)
    words.select { |word| anagram?(word) }
  end

  private

  def anagram?(other_word)
    other_word = Word.new(other_word)
    @word.normalize == other_word.normalize &&
      !@word.equal_ignore_case?(other_word)
  end
end

class Word
  def initialize(word)
    @word = word
  end

  def normalize
    @word.downcase.chars.sort.join
  end

  def equal_ignore_case?(other_word)
    @word.casecmp(other_word) == 0
  end

  def to_str
    @word
  end
end
