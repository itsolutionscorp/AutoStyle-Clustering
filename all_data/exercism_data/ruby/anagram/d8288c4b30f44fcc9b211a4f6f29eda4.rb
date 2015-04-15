class Anagram
  def initialize(word)
    @word = make_case_insensitive(word)
    @char_count = CharCount.new(@word)
  end

  def match(words)
    words.map {|word| make_case_insensitive(word)}
    .reject {|word| word == @word}
    .find_all {|word| CharCount.new(word) == @char_count }
  end

  private
  def make_case_insensitive(word)
    word.downcase
  end
end

class CharCount
  def initialize(word)
    @char_histogram = Hash.new(0)
    word.each_char {|char| @char_histogram[char] += 1 }
  end

  def ==(char_count)
    char_histogram == char_count.char_histogram
  end

  protected
  attr_reader :char_histogram
end
