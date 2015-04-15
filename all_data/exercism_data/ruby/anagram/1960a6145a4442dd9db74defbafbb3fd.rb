class Anagram
  def initialize(word)
    @word = normalize(word)
  end

  def match(candidates)
    candidates.select { |candidate| anagram? candidate }
  end

  private

  def word
    @word
  end

  def normalize(subject)
    subject.downcase
  end

  def sort_letters(subject)
    subject.chars.sort
  end

  def anagram?(candidate)
    candidate = normalize(candidate)

    different_word?(candidate) && same_letters?(candidate)
  end

  def different_word?(candidate)
    candidate != word
  end

  def same_letters?(candidate)
    sort_letters(word) == sort_letters(candidate)
  end
end
