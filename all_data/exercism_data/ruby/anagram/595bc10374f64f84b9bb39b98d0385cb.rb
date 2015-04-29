class Anagram
  def initialize word
    @word = word
  end

  def match words
    words.select { |word| is_different_word?(word) && is_permutation?(word) }
  end

  private
  def is_different_word? word
    @word.downcase != word.downcase
  end

  def is_permutation? word
    @word.downcase.chars.sort == word.downcase.chars.sort
  end
end
