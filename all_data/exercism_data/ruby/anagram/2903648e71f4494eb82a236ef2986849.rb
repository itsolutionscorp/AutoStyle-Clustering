class Anagram
  def initialize(word)
    @word = word.downcase
    @letters = @word.chars.sort
  end

  def match(words)
    words.select do |word|
      word.downcase != @word && @letters == word.downcase.chars.sort
    end
  end
end
