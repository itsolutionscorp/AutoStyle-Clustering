class Anagram
  attr_accessor :word
  def initialize(test_word)
    @test_word = test_word
  end

  def match(word_list)
    word_list.delete_if { |word| word.downcase == @test_word.downcase || word.length != @test_word.length }
    word_list.keep_if { |word| word.downcase.chars.permutation.include? @test_word.downcase.chars }
  end
end
