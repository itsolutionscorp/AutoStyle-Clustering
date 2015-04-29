class Anagram
  def initialize(word)
    @letters = sorted_letters_for_word(word)
  end

  def match(possible_anagrams)
    possible_anagrams.detect { |anagram| anagram?(anagram) }
  end

  private

  def sorted_letters_for_word(word)
    word.downcase.split(//).sort
  end

  def anagram?(possible_anagram)
    sorted_letters_for_word(possible_anagram) == @letters
  end
end
