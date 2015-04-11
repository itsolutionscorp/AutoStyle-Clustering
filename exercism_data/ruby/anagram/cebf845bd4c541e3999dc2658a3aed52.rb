#expect(AnagramMatcher.new(word, anagrams)).to match_array []
class Anagram
  def initialize(word)
    @word = word.downcase
    @characters = @word.chars.sort
  end

  def match(anagrams)
    anagrams = anagrams.map(&:downcase).reject{ |a| a == word }
    anagrams.select{ |anagram| anagram.chars.sort == characters }
  end

  private
  attr_reader :characters, :word
end
