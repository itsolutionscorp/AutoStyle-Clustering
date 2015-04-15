class Anagram

  def initialize anagram
    @anagram = anagram.downcase
  end

  def match possible_anagrams
    possible_anagrams.select do |word|
      normalize_word(@anagram) == normalize_word(word)
    end
  end

  private

  def normalize_word word
    word.chars.sort
  end

end
