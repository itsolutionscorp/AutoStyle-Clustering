class Anagram

  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.each_with_object([]) do |candidate,matches|
      matches << candidate if anagram?(candidate) 
    end
  end

  private

  def anagram?(candidate)
    has_same_characters?(candidate) && is_different_word?(candidate)
  end

  def has_same_characters?(candidate)
    word_chars == sorted_character_set(candidate)  
  end

  def is_different_word?(candidate)
    candidate.casecmp(word) != 0
  end
  
  def word_chars
    @word_chars ||= sorted_character_set(word)
  end

  def sorted_character_set(string)
    string.downcase.each_char.sort  
  end

end
