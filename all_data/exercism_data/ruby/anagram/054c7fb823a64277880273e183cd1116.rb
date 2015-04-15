class Anagram
  attr_reader :subject

  def initialize(subject)
    @subject = subject
  end

  def match(candidates)
    candidates.select { |candidate| anagram?(candidate) }
  end

  private

  def format(input)
    input.downcase.chars.sort
  end

  def anagram?(word)
    format(word) == format(subject) && word.downcase != subject.downcase
  end
end
