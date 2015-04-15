class Anagram
  attr_reader :word
  def initialize(word)
    @word = word.downcase
  end

  def match(word_list)
    word_list.select do |other_word|
      other_word = other_word.downcase
      word != other_word && 
        word.chars.sort == other_word.chars.sort
    end
  end
end
