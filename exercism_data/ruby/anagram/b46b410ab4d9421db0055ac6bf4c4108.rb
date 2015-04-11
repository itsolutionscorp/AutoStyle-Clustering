class Anagram
  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.find_all { |candidate| anagram?(candidate) }
  end

  private

  def anagram?(candidate)
     same_letters?(candidate) && different_word?(candidate)
  end

  def same_letters?(candidate)
    normalize(candidate) == normalize(@word)
  end

  def different_word?(candidate)
    @word.downcase != candidate.downcase
  end

  def normalize(word)
    word.downcase.split('').sort.join
  end
end
