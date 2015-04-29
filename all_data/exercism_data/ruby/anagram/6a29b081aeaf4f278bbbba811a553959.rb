class Anagram

  def initialize word
    @word = word.downcase
  end

  def match array
    matched_words = []
    word_matcher = split_word(@word)
    array.select do |word|
      unchecked_word = split_word(word)
      matched_words.push(word) if unchecked_word == word_matcher
    end
    matched_words
  end

  private

  def split_word word
    word.chars.sort
  end

end
