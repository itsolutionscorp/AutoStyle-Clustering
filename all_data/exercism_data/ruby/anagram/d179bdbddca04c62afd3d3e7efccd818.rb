class Anagram
  attr_reader :subject_letters

  def initialize(subject)
    @subject_letters ||= subject.alphabetize
  end
  
  def match(candidates)
    candidates.select { |word| anagram?(word) }
  end

  def anagram?(word)
    word.alphabetize == subject_letters
  end
end

class String
  def alphabetize
    chars.sort
  end
end
