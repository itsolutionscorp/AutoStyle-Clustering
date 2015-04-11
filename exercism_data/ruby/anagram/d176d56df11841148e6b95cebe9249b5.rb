class Anagram

  attr_reader :word

  def initialize(word = '')
    @word = normalize(word)
  end

  def match(candidates = [])
    candidates.each_with_object([]) { |candidate, anagrams|
      anagrams << candidate if anagram?(candidate)
    }
  end

  private

  def normalize(word)
    word.downcase
  end

  def anagram?(candidate)
    normalized = normalize(candidate)

    same_letters?(normalized) and not identical?(normalized)
  end

  def same_letters?(candidate)
    simplify(word) == simplify(candidate)
  end

  def identical?(candidate)
    word == candidate
  end

  def simplify(string)
    string.chars.sort.join('')
  end
end
