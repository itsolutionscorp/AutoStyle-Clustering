class Anagram
  def initialize(match_word)
    @match_word = match_word.downcase
  end

  def match(words)
    words.select { |word| same?(word) }
  end

  def cypher(word)
    word.split("").sort
  end

  def same?(word)
    word = word.downcase
    unless word == @match_word
      cypher(word) == cypher(@match_word)
    end 
  end
end
