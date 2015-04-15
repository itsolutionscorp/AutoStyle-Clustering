class Anagram

  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select { |candidate| anagram_of? candidate }
  end

  private

  def anagram_of?(candidate)
    return false if candidate.downcase == @word.downcase
    @word.downcase.each_char.sort == candidate.downcase.each_char.sort
  end

end
