class Anagram
  def initialize word
    @word = word.downcase
  end

  def match words
    words.select do |word|
      word.downcase != @word && word.downcase.sort == @word.sort
    end
  end
end

class String
  def sort
    self.chars.sort.join
  end
end
