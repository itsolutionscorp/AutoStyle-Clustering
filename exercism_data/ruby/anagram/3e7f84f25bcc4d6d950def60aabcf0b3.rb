class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(possible_words)
    possible_words.select { |possible_word| anagram?(possible_word) }
  end

  def anagram?(possible_word)
    return false if @word.length != possible_word.length

    @word.chars.sort == possible_word.downcase.chars.sort
  end
end
