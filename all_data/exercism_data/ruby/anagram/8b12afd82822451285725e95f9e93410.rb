class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(words)
    words.select do |word|
      word = word.downcase
      not_identical(word) && same_characters(word)
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
