class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
    @word_characters = sorted_character_list(word)
  end

  def match(test_word_list)
    anagrams = []
    test_word_list.each do |test_word|
      anagrams << test_word if anagram?(test_word)
    end
    anagrams
  end

  private

  def anagram?(test_word)
    if same_length?(@word, word) && !same_word?(@word, test_word)
      test_word_characters = sorted_character_list(test_word)
      test_word_characters == @word_characters
    else
      false
    end
  end

  def sorted_character_list(word)
    character_array = []
    word.each_char { |character| character_array << character.downcase }
    character_array.sort
  end

  def same_word?(word, other_word)
    word.downcase == other_word.downcase
  end

  def same_length?(word, other_word)
    word.length == other_word.length
  end

end
