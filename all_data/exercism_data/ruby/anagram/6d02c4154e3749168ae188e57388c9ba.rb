class Anagram
  def initialize(word)
    @word = Word.new(word)
  end

  def match(words)
    words.select { |word| real_anagram?(word) }
  end

  private

  def real_anagram?(other_word)
    other_word = Word.new(other_word)
    anagram?(other_word) && !same_word?(other_word)
  end

  def same_word?(other_word)
    @word.equal_ignore_case?(other_word)
  end

  def anagram?(other_word)
    @word.normalize == other_word.normalize
  end

end

class Word
  def initialize(word)
    @word = word
  end

  def normalize
    @word.downcase.chars.sort
  end

  def equal_ignore_case?(other_word)
    @word.casecmp(other_word) == 0
  end

  def to_str
    @word
  end
end
