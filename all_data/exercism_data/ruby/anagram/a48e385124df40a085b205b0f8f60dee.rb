class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(array)
    array.
      select { |w| sorted_letters(w) == sorted_letters(word) }.
      reject { |w| w == word }
  end

  private

  def sorted_letters(word)
    word.downcase.chars.to_a.sort
  end
end
