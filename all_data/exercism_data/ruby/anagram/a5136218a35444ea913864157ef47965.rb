class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    words.select { |word| anagram?(@word, word) }
  end

  private

  def normalized(word)
    word.downcase.chars.sort.join
  end

  def anagram?(word1, word2)
    normalized(word1) == normalized(word2) && word1.casecmp(word2) != 0
  end
end
