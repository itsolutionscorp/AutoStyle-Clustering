class Anagram

attr_reader :subject

  def initialize(subject)
    @subject = subject
  end

  def match(candidates)
    candidates.select do |word|
      !identical?(word) && 
        anagram?(word)
    end 
  end

  def letter_frequency(string)
    string.downcase.chars.sort
  end

  def identical?(word)
    subject.downcase == word.downcase
  end

  def anagram?(word)
    letter_frequency(subject) == letter_frequency(word)
  end

end
