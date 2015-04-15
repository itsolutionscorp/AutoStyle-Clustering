class Anagram
  attr_reader :subject_letters

  def initialize(subject)
    @subject_letters = Anagram.alphabetize(subject)
  end
  
  def match(candidates)
    candidates.select { |word| anagram?(word) }
  end

  def anagram?(word)
    Anagram.alphabetize(word) == subject_letters
  end

  def self.alphabetize(word)
    word.chars.sort
  end
end
