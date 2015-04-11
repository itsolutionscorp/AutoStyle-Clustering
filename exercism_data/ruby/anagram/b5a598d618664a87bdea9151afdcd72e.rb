class Anagram

  def initialize(subject)
    @subject = normalize(subject)
  end

  def match(candidates)
    candidates.select { |candidate| anagram?(candidate) }
  end

  private

  def subject
    @subject
  end

  def normalize(word)
    word.downcase
  end

  def anagram?(candidate)
    normalized = normalize(candidate)

    same_letters?(normalized) and not identical?(normalized)
  end

  def same_letters?(candidate)
    letters(subject) == letters(candidate)
  end

  def identical?(candidate)
    subject == candidate
  end

  def letters(string)
    string.chars.sort
  end
end
