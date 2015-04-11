class Anagram
  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(words)
    words.select do |word|
      anagram?(word)
    end
  end

  def anagram?(word)
    organize(word) == organize(@word)
  end

  def organize(word)
    word.split("").sort
  end
end
