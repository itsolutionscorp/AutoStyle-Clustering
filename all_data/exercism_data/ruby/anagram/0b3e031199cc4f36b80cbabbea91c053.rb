class String

  def anagram_of?(word)
    return false if word.downcase == downcase
    word.downcase.chars.sort == downcase.chars.sort 
  end

end

class Anagram

  def initialize(term)
    @term = term
  end

  def match(words)
    words.select { |word| word.anagram_of?(@term) }
  end

end
