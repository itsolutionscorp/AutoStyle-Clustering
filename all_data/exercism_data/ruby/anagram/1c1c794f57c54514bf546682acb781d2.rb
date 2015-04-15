class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(words)
    candidate_matches(words).select { |w| anagram?(w) }
  end

  private

  def candidate_matches(words)
    words.select { |w| w.downcase != @word }
  end

  def anagram?(word)
    word.downcase.chars.sort == @word.chars.sort
  end
end
