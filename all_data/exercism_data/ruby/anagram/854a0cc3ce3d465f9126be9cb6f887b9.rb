class Anagram
  attr_reader :subject

  def initialize(subject)
    @subject = subject
  end
  
  def match(list_to_compare)
    list_to_compare.select { |word| word if anagrammatists_dream(word) }
  end

  def anagrammatists_dream(word)
    same_size(word) && same_letters(word)
  end

  def same_size(word)
    word.size == subject.size
  end

  def same_letters(word)
    word.chars.sort == subject.chars.sort
  end
end
