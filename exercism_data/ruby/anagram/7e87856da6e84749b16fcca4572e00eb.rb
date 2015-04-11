class Anagram

  attr_reader :word

  def initialize(word)
    @word = word
  end

  def anagram?(candidate)
    !identical?(candidate) && contains_same_letters?(candidate)
  end

  def match(candidates)
    candidates.select do |candidate|
      anagram?(candidate)
    end
  end

  private

  def identical?(candidate)
    word.downcase == candidate.downcase
  end

  def contains_same_letters?(candidate)
    normalize(word) == normalize(candidate)
  end

  def normalize(word)
    word.downcase.chars.sort.join
  end
end
