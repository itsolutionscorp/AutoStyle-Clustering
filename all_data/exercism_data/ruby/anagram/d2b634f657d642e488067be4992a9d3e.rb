class Anagram

  def initialize(match_word)
    @match_word = match_word
  end

  def match(word_list)
     @word_list = word_list
     find_match
  end

  private

  attr_reader :match_word, :word_list

  def find_match
    word_list.find_all {|word| characters_match?(word) && !same_word?(word) }
  end

  def characters_match?(word)
    sorted_char_array(word) == sorted_char_array(match_word)
  end

  def same_word?(word)
    word.downcase == match_word.downcase
  end

  def sorted_char_array(word)
    word.downcase.chars.sort
  end
end
