class Anagram

  attr_reader :comparable

  def initialize(word)
    @comparable = SortedWord.new word
  end

  def match(words)
    words.select do |word|
      SortedWord.new(word) == comparable
    end
  end

  private

  class SortedWord

    attr_reader :word

    def initialize(word)
      @word = word.downcase.split('').sort
    end

    def ==(sorted_word)
      word == sorted_word.word
    end

  end

end
