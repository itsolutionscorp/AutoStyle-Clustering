class Anagram
  def initialize(word)
    @word = word
  end

  def match(match_words)
    result = []
    word_letters = @word.downcase.split('').sort
    match_words.each do |match_word|
      match_word_letters = match_word.downcase.split('').sort
      if match_word_letters == word_letters
        result << match_word unless @word.downcase == match_word.downcase
      end
    end
    result
  end
end
