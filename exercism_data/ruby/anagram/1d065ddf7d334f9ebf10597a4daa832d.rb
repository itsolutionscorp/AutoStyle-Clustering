class Anagram
  attr_reader :word, :word_array

  def initialize word
    @word = word
    @word_array = make_word_array
  end

  def match words
    result = (words.map(&:downcase)&word_array) - [word]
    words.select {|word| result.include? word.downcase }
  end

  def make_word_array
    word.split(//).map(&:downcase)
      .permutation(word.length).map(&:join)
  end
end
