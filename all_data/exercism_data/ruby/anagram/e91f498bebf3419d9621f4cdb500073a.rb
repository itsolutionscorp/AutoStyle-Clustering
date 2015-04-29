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

  class Comparable < Array

    def initialize(word)
      super word.downcase.split('').sort
    end

  end

end
