class Anagram
  def initialize(subject)
    @subject = normalize(subject)
  end

  def match(candidates)
    candidates.find_all { |candidate|
      anagram?(normalize(candidate))
    }
  end

  private
  def normalize(word)
    word.downcase
  end

  def anagram?(candidate)
    @subject != candidate && letters(@subject) == letters(candidate)
  end

  def letters(word)
    word.chars.sort
  end
end
