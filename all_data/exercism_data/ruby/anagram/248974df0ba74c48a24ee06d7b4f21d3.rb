class Anagram

  def initialize(word)
    @word = word
    @anagram_matches = []
  end

  def match(candidates)
    candidates.each { |candidate| @anagram_matches << candidate if anagrams?(candidate, @word) }
    @anagram_matches
  end

  def prepare(word)
    word.downcase.split('')
  end

  def anagrams?(word1, word2)
    prepare(word1) != prepare(word2) && prepare(word1).sort == prepare(word2).sort
  end

end
