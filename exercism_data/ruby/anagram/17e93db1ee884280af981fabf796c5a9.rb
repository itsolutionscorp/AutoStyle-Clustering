class Anagram
  attr_accessor :subject
  
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
    letter_counts = count_letters(candidate)
    subject_letter_counts = count_letters(@subject)
    letter_counts.eql?(subject_letter_counts)
  end
  
  def count_letters(word)
    #{"b"=>1,"d"=>3,"e"=>4}
    letter_counts = Hash.new(0)
    word.each_char do |letter|
      letter_counts[letter] += 1
    end
    letter_counts
  end

  def identical_word?(word)
    @subject == word
  end

end
