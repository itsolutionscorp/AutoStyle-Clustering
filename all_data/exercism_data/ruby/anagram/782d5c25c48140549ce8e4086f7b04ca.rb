class Anagram
  attr_reader :subject

  def initialize(subject)
    @subject = subject
  end

  def match(candidates)
    candidates.select { |candidate| other?(candidate) && anagram?(candidate) }
  end

  def other?(candidate)
    downcase(subject) != downcase(candidate)
  end

  def anagram?(candidate)
    normalize(subject) == normalize(candidate)
  end

  private

  def downcase(word)
    word.downcase
  end

  def normalize(word)
    downcase(word).chars.sort
  end
end
