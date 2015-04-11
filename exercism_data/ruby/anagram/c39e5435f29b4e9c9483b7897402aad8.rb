class Anagram
  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select do |candidate|
      word_is_anagram_for?(candidate, @word)
    end
  end

  private
  def word_is_anagram_for?(candidate, word)
    candidate = candidate.downcase
    word = word.downcase

    if word == candidate
      return false
    end

    word.each_char.sort == candidate.each_char.sort
  end
end
