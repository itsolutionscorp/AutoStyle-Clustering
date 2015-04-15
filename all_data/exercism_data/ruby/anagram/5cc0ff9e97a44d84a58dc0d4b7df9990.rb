class Anagram
  def initialize word
    @word = word
  end

  def match words
    words.select &method(:anagram?)
  end

  private
  def anagram? word
    different_word?(word) && permutation?(word) 
  end

  def different_word? word
    @word.downcase != word.downcase
  end

  def permutation? word
    @word.downcase.chars.sort == word.downcase.chars.sort
  end
end
