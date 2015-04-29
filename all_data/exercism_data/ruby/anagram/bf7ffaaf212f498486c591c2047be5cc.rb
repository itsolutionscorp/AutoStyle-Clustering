class Anagram
  def initialize(word)
    @word = word
  end

  def match(anagrams)
    anagrams.select { |w| anagram?(w) }
  end

  private

  attr_reader :word

  def anagram?(string)
    same_characters?(string) && different_word?(string)
  end

  def different_word?(string)
    string.downcase != word.downcase
  end

  def same_characters?(string)
    @characters ||= word.downcase.chars.sort
    string.downcase.chars.sort == @characters
  end
end
