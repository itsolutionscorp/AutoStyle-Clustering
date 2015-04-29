class Anagram

  def initialize word_anagram
    @word_anagram = word_anagram.downcase
  end

  def match list_of_words
    list_of_words.select do |word|
      normalize_word(@word_anagram) == normalize_word(word)
    end
  end

  private

  def normalize_word word
    word.chars.sort
  end

end
