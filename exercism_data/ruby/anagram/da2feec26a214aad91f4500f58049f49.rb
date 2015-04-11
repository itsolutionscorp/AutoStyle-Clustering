class Anagram

  attr_reader :comparable

  def initialize(word)
    @comparable = Comparable.new word
  end

  def match(words)
    words.select do |word|
      Comparable.new(word) == comparable
    end
  end

  private

  class Comparable < String

    def initialize(word)
      super comparableize(word)
    end

    private

    def comparableize(word)
      sort word.downcase
    end

    def sort(word)
      word.chars.sort.join ''
    end

  end

end
