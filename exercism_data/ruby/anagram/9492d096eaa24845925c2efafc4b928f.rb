class Anagram
  def initialize(word)
    @word = word
  end

  def match(anagrams)
    anagrams.select do |anagram| 
      anagram.chars.sort == @word.chars.sort
    end
  end
end
