class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    words.select do |word|
      letters(word) == letters(@word)
    end
  end

  def letters(word)
    word.split("").sort
  end
end
