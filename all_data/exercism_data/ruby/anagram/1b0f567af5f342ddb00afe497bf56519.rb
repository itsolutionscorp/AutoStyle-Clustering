class Anagram

  attr_reader :subject

  def initialize(subject)
    @subject = subject
  end

  def match(candidates)
    candidates.select do |candidate|
      anagram?(candidate)
    end
  end

  def anagram?(candidate)
    same_word?(candidate) && same_characters?(candidate)
  end

  def same_word?(candidate)
    subject.downcase != candidate.downcase
  end

  def same_characters?(candidate)
    sorted_letters(candidate) == sorted_letters(subject)
  end

  def sorted_letters(word)
    word.downcase.chars.sort.join
  end


end
