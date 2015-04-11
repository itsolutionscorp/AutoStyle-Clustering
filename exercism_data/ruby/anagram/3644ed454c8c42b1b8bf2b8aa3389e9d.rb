class Anagram
  def initialize(match_word)
    @match_word = match_word
    @match_word_letters = letters(match_word)
  end
  
  def match(words)
    words.select do |word|
      next if word.downcase == @match_word.downcase
      letters(word) == @match_word_letters
    end.uniq
  end
  
  private
  
  def letters(word)
    word.downcase.split('').sort
  end
end
