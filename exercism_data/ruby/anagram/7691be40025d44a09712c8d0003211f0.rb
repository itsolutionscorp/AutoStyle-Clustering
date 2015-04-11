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

  def anagram?(string)
    return false if string.casecmp(word) == 0
    word_chars == sorted_character_set(string)
  end
  
  def word_chars
    @word_chars ||= sorted_character_set(word)
  end

  def sorted_character_set(string)
    string.downcase.each_char.sort  
  end

end
