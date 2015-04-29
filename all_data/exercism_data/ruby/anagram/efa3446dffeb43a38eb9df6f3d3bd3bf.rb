class Anagram

  def initialize(match_word)
    @match_word = match_word
  end

  def match(word_list)
     word_list.find_all {|word| characters_match(word) && !same_word(word) }
  end

  private

  attr_reader :match_word 

  def characters_match(word)
    word.downcase.chars.sort == match_word.downcase.chars.sort
  end

  def same_word(word)
    word.downcase == match_word.downcase
  end
end
