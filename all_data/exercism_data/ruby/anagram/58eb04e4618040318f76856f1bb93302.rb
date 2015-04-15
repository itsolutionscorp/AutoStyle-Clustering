class Anagram
  def initialize(test_word)
    @test_word = test_word.downcase
  end

  def match(word_list)
    word_list.select { |word| word.downcase != @test_word && word.downcase.chars.sort == @test_word.chars.sort }
  end
end
