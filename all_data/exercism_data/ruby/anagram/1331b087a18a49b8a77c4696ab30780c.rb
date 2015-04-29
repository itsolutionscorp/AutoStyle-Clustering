class Anagram
  def initialize(word)
    @value = Match.new(word)
  end

  def match(words)
    words.select do |w| 
      w.downcase != @value.word && w.downcase.chars.sort == @value.chars
    end
  end
end

class Match
  attr_reader :word, :chars

  def initialize(word)
    @word = word.downcase
    @chars = @word.chars.sort
  end
end
