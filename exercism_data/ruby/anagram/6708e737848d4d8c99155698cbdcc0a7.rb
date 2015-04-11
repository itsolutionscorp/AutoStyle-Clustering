class Anagram
  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select do |candidate|
      anagram?(candidate) && !same_word?(candidate)
    end
  end

  private

  attr_reader :word

  def same_word?(candidate)
    word.downcase == candidate.downcase
  end

  def anagram?(candidate)
    normalize(word) == normalize(candidate)
  end

  def normalize(str)
    str.downcase.chars.sort
  end
end
