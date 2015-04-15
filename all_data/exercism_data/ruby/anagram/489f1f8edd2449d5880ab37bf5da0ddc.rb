class Anagram
  def initialize word
    @word = word.downcase
    @letters = @word.chars.sort
  end

  def match list
    list.select do |word|
      candidate = word.downcase
      candidate != @word and candidate.chars.sort == @letters
    end
  end
end
