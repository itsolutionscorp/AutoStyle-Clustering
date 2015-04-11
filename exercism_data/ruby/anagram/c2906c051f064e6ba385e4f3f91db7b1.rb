class Anagram
  def initialize(test_word)
    @test_word = test_word.downcase
  end

  def match(word_list)
    word_list.delete_if { |word| word.downcase == @test_word }
    word_list.keep_if { |word| word.downcase.chars.sort == @test_word.chars.sort }
  end
end
