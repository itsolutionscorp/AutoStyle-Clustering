class Anagram

  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select { |candidate| anagram?(candidate) }
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
    string.downcase.chars.sort  
  end

end
