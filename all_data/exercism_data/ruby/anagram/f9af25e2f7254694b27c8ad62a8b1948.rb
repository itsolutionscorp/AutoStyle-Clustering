class Anagram
  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select do |candidate|
      is_anagram?(candidate) == true && is_same_word?(candidate) == false
    end
  end

  def is_same_word?(candidate)
    @word.downcase == candidate.downcase
  end

  def is_anagram?(candidate)
    normalize(@word) == normalize(candidate)
  end

  def normalize(str)
    str.downcase.chars.sort
  end
end
