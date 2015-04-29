class Anagram

attr_reader :subject

  def initialize(subject)
    @subject = subject
  end

  def match(candidates)
    candidates.select do |word|
      !identical?(word) && 
        same_letters?(word)
    end 
  end

  def organize_letters(string)
    string.downcase.chars.sort
  end

  def identical?(word)
    subject.downcase == word.downcase
  end

  def same_letters?(word)
    organize_letters(subject) == organize_letters(word)
  end

end
