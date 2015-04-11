class Anagram
  attr_reader :letters, :subject

  def initialize(word)
    @letters = letters_from(word)
    @subject = word
  end

  def match(words)
    words.select do |word| 
      words_match?(letters, letters_from(word)) unless subject.downcase == word.downcase  
    end
  end

private

  def letters_from(word)
    word.downcase.chars.sort
  end

  def words_match?(word1, word2)
    word1 == word2
  end
end
