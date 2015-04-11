class Anagram

  attr_reader :word

  def initialize(word)
    @word = normalize(word)
  end

  def match(candidates)
    candidates.select { |candidate|
      anagram?(candidate)
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
    ordered_chars(word) == ordered_chars(candidate)
  end

  def identical?(candidate)
    word == candidate
  end

  def ordered_chars(string)
    string.chars.sort
  end
end
