class Anagram
  def initialize(word)
    @word = word.downcase
    @letters = letters_for(@word)
  end

  def match(words)
    words.select do |word|
      normalized_word = word.downcase
      normalized_word != @word && @letters == letters_for(normalized_word)
    end
  end

  private

  def letters_for(word)
    word.chars.sort
  end
end
