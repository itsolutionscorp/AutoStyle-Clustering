class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
    @word_characters = sorted_character_list(word)
  end

  def match(test_word_list)
    test_word_list.select { |word| anagram?(word) }
  end

  private

  def anagram?(test_word)
    if same_length?(@word, word) && !same_word?(@word, test_word)
      sorted_character_list(test_word) == @word_characters
    end
  end

  def sorted_character_list(word)
    character_array = []
    word.downcase.chars.sort
    #character_array.sort
  end

  def same_word?(word, other_word)
    word.downcase == other_word.downcase
  end

  def same_length?(word, other_word)
    word.length == other_word.length
  end

end
