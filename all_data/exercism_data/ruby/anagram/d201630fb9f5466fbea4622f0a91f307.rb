class Anagram
  def initialize(subject)
    @subject = subject.downcase
  end

  def match(words)
    words.select { |candidate| anagram?(candidate.downcase) }
  end

  private

  def anagram?(candidate)
    same_letters?(candidate) && different_word?(candidate)
  end

  def different_word?(candidate)
    candidate != @subject
  end

  def same_letters?(candidate)
    letters_in_word(candidate) == letters_in_word(@subject)
  end

  def letters_in_word(word)
    word.chars.sort
  end
end
