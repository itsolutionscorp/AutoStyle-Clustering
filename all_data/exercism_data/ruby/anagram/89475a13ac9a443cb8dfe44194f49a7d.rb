class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(words)
    words.select do |other_word|
      other_word = other_word.downcase
      @word.chars.sort == other_word.chars.sort if @word != other_word
    end
  end
end
