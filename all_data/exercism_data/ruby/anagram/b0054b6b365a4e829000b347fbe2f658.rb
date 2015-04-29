class Anagram
  attr_reader :word
  def initialize word
    @word = word
  end

  def match words
    word_array = word.split(//)
    word_array = word_array.permutation(word_array.length).map(&:join)
    words&word_array
  end
end
