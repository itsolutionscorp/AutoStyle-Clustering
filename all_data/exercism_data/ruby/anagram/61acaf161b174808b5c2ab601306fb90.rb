class Anagram
  def initialize(anagram)
    @anagram = anagram
  end

  attr_reader :anagram

  def match(words)
    words.select do |word|
      same_word?(word) && different_word?(word)
    end
  end

  private
  def different_word?(word)
    anagram.downcase != word.downcase
  end

  def sort_chars_for(word)
    word.downcase.chars.sort
  end

  def same_word?(word)
    sort_chars_for(anagram) == sort_chars_for(word)
  end

end
