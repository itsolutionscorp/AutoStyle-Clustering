class Anagram
  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def match(word_list)
    word_list.select { |match| is_anagram?(match) }
  end

  private

  def is_anagram?(match)
    return false if match.downcase == word
    match.downcase.chars.sort == word.chars.sort
  end

end
