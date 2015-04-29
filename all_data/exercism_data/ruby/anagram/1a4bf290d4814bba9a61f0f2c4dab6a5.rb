class Anagram
  def initialize(word)
    @word = word
    @word_chars = word.downcase.chars.sort
  end

  def match(words)
    words.select do |word|
      @word_chars == word.downcase.chars.sort unless @word == word.downcase
    end
  end
end
