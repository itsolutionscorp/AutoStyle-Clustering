class Anagram

  def initialize(word)
    @word = word.downcase
  end

  def match(candidates)
    candidates.select do |c|
      anagram?(c) && different_word?(c)
    end
  end

  def anagram?(candidate)
    candidate.downcase.chars.sort == @word.chars.sort
  end

  def different_word?(candidate)
    candidate.downcase != @word
  end

end
