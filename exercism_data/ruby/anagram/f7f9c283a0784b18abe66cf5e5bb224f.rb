class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    words.select { |word| word.downcase.chars.sort == @word.downcase.chars.sort unless @word.downcase == word.downcase }
  end
end
