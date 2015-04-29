class Anagram

  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select { |candidate| anagrams?(candidate, @word) }
  end

  def anagrams?(word1, word2)
    prepare(word1) != prepare(word2) && prepare(word1).sort == prepare(word2).sort
  end

  def prepare(word)
    word.downcase.chars
  end

end
