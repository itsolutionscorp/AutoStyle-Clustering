class Anagram
  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def match(candidates)
    candidates.select { |candidate| anagram?(candidate.downcase) }
  end

  def anagram?(candidate)
    word.chars.sort == candidate.chars.sort && word != candidate
  end
end
