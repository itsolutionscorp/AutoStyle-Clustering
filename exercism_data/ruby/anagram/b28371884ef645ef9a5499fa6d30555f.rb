class Anagram
  attr_reader :word
  def initialize(word)
    @word = word.downcase
  end

  def match(array)
    array.select { |anagram| anagram.downcase.chars.sort == word.chars.sort unless anagram.downcase == word }
  end
end
