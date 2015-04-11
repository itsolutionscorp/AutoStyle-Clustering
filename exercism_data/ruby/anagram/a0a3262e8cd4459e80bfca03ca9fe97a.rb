class Anagram

  def initialize(word)
    @word = word.downcase
  end

  def match(word_arr)
    word_arr.each_with_object([]) do |word, array|
      word.downcase
      array << word if anagram?(word) && !array.include?(word)
    end
  end

  private

  def anagram?(word_to_test)
    return false if @word == word_to_test
    sorted_word = @word.chars.sort.join
    sorted_word_to_test = word_to_test.chars.sort.join
    sorted_word == sorted_word_to_test
  end
end
