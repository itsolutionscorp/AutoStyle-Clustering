class Anagram
  attr_reader :subject

  def initialize(subject)
    @subject = subject
  end
  
  def match(candidates)
    candidates.select { |word| anagram?(word) }
  end

  def anagram?(word)
    @subject_letters ||= subject.alphabetize
    word.alphabetize == @subject_letters
  end
end

class String
  def alphabetize
    chars.sort
  end
end
