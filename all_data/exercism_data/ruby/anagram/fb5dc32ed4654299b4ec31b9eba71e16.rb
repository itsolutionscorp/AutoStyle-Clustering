class Anagram

  def initialize(word)
    @word = word
  end

  def match(potential_anagrams)
    potential_anagrams = potential_anagrams.reject(same_words?)
    potential_anagrams = potential_anagrams.reject do |anagram|
      anagram.downcase == @word
    end
    frequencies = letters(@word.downcase)
    potential_anagrams.select do |anagram|
      letters(anagram.downcase) == frequencies
    end

  end

  def same_words?(word)
    word == @word
  end

  def letters(word)
    word.chars.sort
  end


end
