class Anagram
  def initialize(match_word)
    @match_word = match_word.downcase
  end

  def match(words)
    words.select { |word| cypher(word) }
  end

  def split_and_sort(word)
    word.split("").sort
  end

  def cypher(word)
    word = word.downcase
    unless word == @match_word
      split_and_sort(word) == split_and_sort(@match_word)
    end 
  end
end
