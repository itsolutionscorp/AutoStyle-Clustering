class Anagram

  def initialize(match_word)
    @match_word = match_word
  end

  def match(word_list)
     find_match(word_list)
  end

  private

  attr_reader :match_word 

  def find_match(word_list)
    word_list.find_all {|word| not_same_word?(word) && characters_match?(word) }
  end

  def characters_match?(word)
    word.downcase.chars.sort == match_word.downcase.chars.sort
  end

  def not_same_word?(word)
    word.downcase != match_word.downcase
  end
end
