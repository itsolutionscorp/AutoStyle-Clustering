class Anagram
  def initialize(word)
    @value = Match.new(word)
  end

  def match(words)
    words.select do |w| 
      w.downcase != @value.word && w.downcase.chars.sort == @value.sorted
    end
  end
end

class Match
  attr_reader :word, :sorted

  def initialize(word)
    @word = word.downcase
    @sorted = @word.chars.sort
  end
end
