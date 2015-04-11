class Anagram

  attr_reader :sorted_word

  def initialize(word)
    @sorted_word = SortedWord.new word
  end

  def match(words)
    words.select do |word|
      SortedWord.new(word) == sorted_word
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
