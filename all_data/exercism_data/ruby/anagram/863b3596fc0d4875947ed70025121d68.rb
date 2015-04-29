class Anagram
  def initialize(phrase)
    @phrase = phrase
  end

  def match(words)
    words.select do |word|
      sorted_letters(word) == sorted_letters(@phrase)
    end
  end

  private

  def sorted_letters(word)
    word.downcase.split("").sort
  end
end
