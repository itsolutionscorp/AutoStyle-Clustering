class Anagram

  def initialize(word)
    @word = word.downcase
    @sorted_word_characters = @word.chars.sort
  end

  def match(anagrams)
    anagrams.select {|anagram| bingo?(anagram) }
  end

  private

  def bingo?(anagram)
    anagram = anagram.downcase
    return false if @word == anagram
    return @sorted_word_characters == anagram.chars.sort
  end

end
