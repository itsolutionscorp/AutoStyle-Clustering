class Anagram
  attr_reader :subject
  
  def initialize(word)
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
    letter_frequency(candidate) == letter_frequency(subject)
  end
  
  def letter_frequency(letters)
    Hash.new(0).tap do |letter_frequencies|
      letters.each_char { |letter| letter_frequencies[letter] += 1 }
    end
  end

  def identical_word?(word)
    subject == word
  end

end
