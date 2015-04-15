class Anagram
  attr_reader :subject

  def initialize(subject)
    @subject = subject
  end
  
  def match(list_to_compare)
    list_to_compare.select { |word| same_letters?(word) }
  end

  def same_letters?(word)
    word.sort_chars == subject.sort_chars
  end
end

class String
  def sort_chars
    chars.sort
  end
end
