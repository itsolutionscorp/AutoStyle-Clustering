class Anagram

  def initialize(word)
    @word = word.downcase
    @sorted_word_characters = @word.chars.sort
  end

  def match(anagrams)
    anagrams.each_with_object([]) do |anagram, result|
      result << anagram if bingo? anagram
    end
  end

  private

  def bingo?(anagram)
    anagram = anagram.downcase
    return false if @word == anagram
    return @sorted_word_characters == anagram.chars.to_a.sort
  end

end
