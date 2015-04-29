class Anagram
  def initialize(word)
    @word = Word.new(word)
  end

  def match(words)
    words.select do |other_word|
      other_word = Word.new(other_word)
      @word.chars_count == other_word.chars_count if @word != other_word
    end
  end
end

class Word
  include Enumerable
  include Comparable

  def initialize(word)
    @word = word.downcase
  end

  def each(&block)
    @word.chars.each(&block)
  end

  def chars_count
    Hash[map do |char|
      [char, count(char)]
    end]
  end

  def <=>(other_word)
    @word <=> other_word
  end
end
