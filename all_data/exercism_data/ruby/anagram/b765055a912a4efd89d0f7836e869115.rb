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
  match_list = Array.new(0)
    word_list.each do |word| 
      compare_to_match_word(word) { match_list << word } 
    end
  match_list
  end

  def compare_to_match_word(word)
    if characters_match?(word) && !same_word?(word)
      yield
    end
  end

  def characters_match?(word)
    sorted_char_array(word) == sorted_char_array(match_word)
  end

  def same_word?(word)
    word.downcase == match_word.downcase
  end

  def sorted_char_array(word)
    word.downcase.split(//).sort
  end
end
