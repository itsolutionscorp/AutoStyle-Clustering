class Anagram

  def initialize anagram
    @anagram = anagram
  end

  def match possible_anagrams
    possible_anagrams.select do |word|
      normalize(@anagram) == normalize(word)
    end
  end

  #private

  def normalize word
    word.downcase.chars.sort
  end

end
