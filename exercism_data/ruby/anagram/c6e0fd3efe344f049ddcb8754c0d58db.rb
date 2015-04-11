class Anagram
  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def match(words)
    words.select do |anagram|
      anagram = anagram.downcase
      not_identical(anagram) && same_characters(anagram)
    end
  end

  private

    def not_identical(anagram)
      word != anagram
    end

    def same_characters(anagram)
      word.chars.sort == anagram.chars.sort
    end
end
