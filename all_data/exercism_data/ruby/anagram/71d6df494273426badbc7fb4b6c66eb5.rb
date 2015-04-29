class Anagram
  attr_reader :subject
  
  def initialize(word="")
    @subject = word.downcase
  end
  
  def match(candidates)
    candidates.select { |candidate| anagram?(candidate.downcase) }
  end
  
  private
  
  def anagram?(candidate)
    !identical_word?(candidate) && same_letter_frequency?(candidate)
  end
  
  def same_letter_frequency?(candidate)
    count_letters(candidate) == count_letters(subject)
  end
  
  def count_letters(letters)
    letter_counts = Hash.new(0)
    letters.each_char { |letter| letter_counts[letter] += 1 }
    return letter_counts
  end

  def identical_word?(word)
    subject == word
  end

end
