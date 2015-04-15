class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    sorted_characters = @word.chars.sort

    words.select do |anagram| 
      anagram.chars.sort == sorted_characters
    end
  end
end
