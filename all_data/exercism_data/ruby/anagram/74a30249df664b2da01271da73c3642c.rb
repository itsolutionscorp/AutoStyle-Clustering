class Anagram
  def initialize(word)
    @word = word.downcase
    @sorted_characters = sort_characters(word)
  end

  def match(list)
    list.select { |word| anagram?(word) }
  end

  private

  def anagram?(word)
    word.downcase != @word && sort_characters(word) == @sorted_characters
  end

  def sort_characters(word)
    word.downcase.chars.sort.join
  end
end
