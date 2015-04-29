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
  end

  def match_word?(value)
    @word.downcase == value.downcase
  end

  def match_chars?(values)
    @word.lowercase_array == values.lowercase_array
  end
end

class String
  def lowercase_array
    self.downcase.chars.sort
  end
end
