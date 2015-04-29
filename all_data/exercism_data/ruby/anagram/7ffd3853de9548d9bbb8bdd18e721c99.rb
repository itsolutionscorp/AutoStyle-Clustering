class Anagram
  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select do |candidate|
      not same_word?(candidate) and same_letters?(candidate)
    end
  end

  protected

  def same_word?(candidate)
    @word.downcase == candidate.downcase
  end

  def same_letters?(candidate)
    sorted_letters(@word) == sorted_letters(candidate)
  end

  def sorted_letters(word)
    word.downcase.chars.sort
  end
end
