class Anagram
  attr_reader :word
  
  def initialize(word)
    @word = word
  end

  def match(word_list)
    @down_word ||= word.downcase
    @word_char_list ||= char_list_from(@down_word)
    word_list.select { |candidate|
      does_match?(candidate.downcase)
    }
  end

  private

  def char_list_from(word)
    word.chars.sort
  end

  def does_match?(down_candidate)
    down_candidate != @down_word &&
      char_list_from(down_candidate) == @word_char_list
  end
end
