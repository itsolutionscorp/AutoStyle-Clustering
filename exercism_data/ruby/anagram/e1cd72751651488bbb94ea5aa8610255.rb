class Anagram

  def initialize(word)
    @word = word
  end

  def match(potential_anagrams)
    potential_anagrams = discard_same_words(potential_anagrams)
    letters = letters(@word.downcase)
    potential_anagrams.select do |anagram|
      letters(anagram.downcase) == letters
    end
  end

  def discard_same_words(potential_anagrams)
    same_words = lambda { |anagram| anagram.downcase == @word }
    potential_anagrams.reject(&same_words)
  end

  def letters(word)
    word.chars.sort
  end


end
