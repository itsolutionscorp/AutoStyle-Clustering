class Anagram
  def initialize word
    @word = word.downcase
    @letters = @word.chars.sort
  end

  def match words
    words.select { |w| w.downcase!=@word && w.downcase.chars.sort == @letters }
  end
end
