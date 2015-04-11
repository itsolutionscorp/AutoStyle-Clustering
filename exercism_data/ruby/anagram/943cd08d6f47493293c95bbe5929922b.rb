class Anagram
  attr_reader :original_word

  def initialize(word)
     @original_word = word
  end

  def match(word_list)
    word_list.select { |word| anagram?(word) && !duplicate?(word) }
  end

private
  def anagram?(word)
    sorted_letters(word) == sorted_letters(original_word)
  end

  def duplicate?(word)
    word.downcase == original_word.downcase
  end

  def sorted_letters(word)
    word.downcase.chars.sort
  end


end
