class Anagram

  def initialize(str)
    @word = to_word(str)
  end

  def match(str_arr)
    str_arr.each_with_object([]) do |str, array|
      word = to_word(str)
      array << word if anagram?(word) && !array.include?(word)
    end
  end

  private

  def to_word(str)
    str.downcase
  end

  def anagram?(word_to_test)
    return false if @word == word_to_test
    sort_word(@word) == sort_word(word_to_test)
  end

  def sort_word(word)
    word.chars.sort.join
  end
end
