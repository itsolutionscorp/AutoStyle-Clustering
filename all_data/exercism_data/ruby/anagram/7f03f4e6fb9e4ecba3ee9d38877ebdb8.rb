class Anagram
  attr_reader :starting_word

  def initialize(starting_word)
    @starting_word = organize(starting_word)
  end

  def match(words)
    words.select do |word|
      anagram?(word)
    end
  end

  def anagram?(word)
    organize(word) == @starting_word
  end

  def organize(word)
    word.split("").sort
  end
end
