class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    words.select do |word|
      next if @word == word.downcase
      @word.downcase.chars.to_a.sort == word.downcase.chars.to_a.sort
    end
  end
end
