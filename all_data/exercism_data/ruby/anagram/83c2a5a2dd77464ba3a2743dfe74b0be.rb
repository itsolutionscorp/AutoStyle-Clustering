class Anagram
  def initialize(word)
    @original = word
    @fingerprint = fingerprint(word)
  end

  def match(words)
    words.select { |word| anagram?(word) }
  end

  private

  def anagram?(word)
    different?(word, @original) && fingerprint(word) == @fingerprint
  end

  def different?(word1, word2)
    word1.downcase != word2.downcase
  end

  def fingerprint(word)
    word.downcase.chars.sort
  end
end
