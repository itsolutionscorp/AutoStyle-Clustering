class Anagram

  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select { |candidate| anagram_of? candidate }
  end

  private

  def anagram_of?(candidate)
    candidate, word = candidate.downcase, @word.downcase
    return false if candidate == word
    alphagram(word) == alphagram(candidate)
  end

  def alphagram(word)
    word.chars.sort
  end

end
