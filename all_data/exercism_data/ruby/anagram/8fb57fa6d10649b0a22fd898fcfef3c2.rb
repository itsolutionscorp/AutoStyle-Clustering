class Anagram
  def initialize(word)
    @value = Match.new(word)
  end

  def match(words)
    words.select { |w| !@value.match_word?(w) && @value.match_chars?(w) }
  end
end

class Match
  def initialize(word)
    @word = word
    @sorted = @word.downcase.chars.sort
  end

  def match_word?(value)
    @word.downcase == value.downcase
  end

  def match_chars?(values)
    @sorted == values.downcase.chars.sort
  end
end
