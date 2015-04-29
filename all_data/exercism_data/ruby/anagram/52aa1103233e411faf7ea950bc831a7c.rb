class Anagram
  def initialize(word)
    @down_word = word.downcase
    @word_char_list = char_list_from(@down_word)
  end

  def match(word_list)
    word_list.select { |candidate| does_match?(candidate) }
  end

  private

  def char_list_from(word)
    word.chars.sort
  end

  def does_match?(candidate)
    down_candidate = candidate.downcase
    down_candidate != @down_word &&
      char_list_from(down_candidate) == @word_char_list
  end
end
