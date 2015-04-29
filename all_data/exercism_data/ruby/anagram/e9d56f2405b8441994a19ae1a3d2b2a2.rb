class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(words)
    words.select do |word| 
      not_identical(word.downcase) && 
      same_characters(word.downcase)
    end
  end

  private

  def not_identical(word)
    @word != word
  end

  def same_characters(word)
    @word.chars.sort == word.chars.sort
  end
end
