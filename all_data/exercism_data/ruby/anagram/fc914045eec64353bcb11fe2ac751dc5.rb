class Anagram
  def initialize(word)
    @subject_word = word
  end

  def match(words)
    words.select { |word| WordComparison.new(word, @subject_word).anagrams? }
  end
end

class WordComparison
  def initialize(word1, word2)
    @word1 = normalize(word1)
    @word2 = normalize(word2)
  end

  def anagrams?
    same_letters? and not same_word?
  end

  private
  attr_reader :word1, :word2

  def same_letters?
    sorted_letters(word1) == sorted_letters(word2)
  end

  def same_word?
    word1 == word2
  end

  private
  def normalize(word)
    word.downcase
  end

  def sorted_letters(s)
    s.split(//).sort
  end
end
